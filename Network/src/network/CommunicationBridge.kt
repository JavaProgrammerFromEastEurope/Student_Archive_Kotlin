package network

import java.io.*
import java.net.Socket
import java.nio.charset.StandardCharsets

class CommunicationBridge(
    private val eventListener: TCPConnectionListener,
    private val socket: Socket
) {
    private lateinit var rxThread: Thread
    private var bufferedWriter: BufferedWriter

    constructor(
        eventListener: TCPConnectionListener,
        ipAddress: String?, port: Int
    ) : this(eventListener, Socket(ipAddress, port))

    @Throws(IOException::class, ClassNotFoundException::class)
    fun readMessage(): Message {
        val ois = ObjectInputStream(socket.getInputStream())
        return ois.readObject() as Message
    }

    @Synchronized
    fun sendMessage(message: Message?) {
        try {
            val oos = ObjectOutputStream(socket.getOutputStream())
            oos.writeObject(message)
            oos.flush()
        } catch (e: IOException) {
            eventListener.onException(this, e)
            disconnect()
        }
    }

    @Synchronized
    fun sendString(value: String) {
        try {
            bufferedWriter.write(value.trimIndent())
            bufferedWriter.flush()
        } catch (e: IOException) {
            eventListener.onException(this, e)
            disconnect()
        }
    }

    @Synchronized
    fun disconnect() {
        rxThread.interrupt()
        try {
            socket.close()
        } catch (e: IOException) {
            eventListener.onException(this, e)
        }
    }

    override fun toString(): String {
        return String.format(
            "TCPConnection: %s: %d",
            socket.inetAddress, socket.port
        )
    }

    init {
        val bufferedReader = BufferedReader(
            InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
        )
        bufferedWriter = BufferedWriter(
            OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
        )
        rxThread = Thread {
            try {
                eventListener.onConnectionReady(this@CommunicationBridge)
                while (!rxThread.isInterrupted) {
                    eventListener.onReceiveMessage(
                        this@CommunicationBridge,
                        readMessage()
                    )
                }
            } catch (e: IOException) {
                eventListener.onException(this@CommunicationBridge, e)
            } catch (e: ClassNotFoundException) {
                eventListener.onException(this@CommunicationBridge, e)
            } finally {
                eventListener.onDisconnect(this@CommunicationBridge)
            }
        }
        rxThread.start()
    }
}