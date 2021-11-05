import commands.CommandFactory
import network.CommunicationBridge
import network.ITCPConnectionListener
import network.Message
import java.io.IOException
import java.net.ServerSocket

class Server private constructor() : ITCPConnectionListener {
    private val connections = ArrayList<CommunicationBridge?>()
    @Synchronized
    override fun onConnectionReady(tcpConnection: CommunicationBridge?) {
        connections.add(tcpConnection)
        println("Client connected: $tcpConnection")
    }

    @Synchronized
    override fun onReceiveString(tcpConnection: CommunicationBridge?, value: String?) {
        sendToAllConnections(value)
    }

    @Synchronized
    override fun onReceiveMessage(tcpConnection: CommunicationBridge?, message: Message?) {
        try {
            tcpConnection!!.sendMessage(
                CommandFactory.instance.getCommand(message!!.name)!!.execute(message)
            )
            connections.remove(tcpConnection)
            tcpConnection.disconnect()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    @Synchronized
    override fun onDisconnect(tcpConnection: CommunicationBridge?) {
        connections.remove(tcpConnection)
        tcpConnection!!.sendString("Client disconnected: $tcpConnection")
    }

    @Synchronized
    override fun onException(tcpConnection: CommunicationBridge?, e: Exception?) {
        println("TCPConnection exception: $e")
    }

    private fun sendToAllConnections(value: String?) {
        println(value)
        for (connection in connections) connection!!.sendString(value!!)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Server()
        }
    }

    init {
        println("Server running...")
        try {
            ServerSocket(4444).use { serverSocket ->
                while (true) {
                    try {
                        CommunicationBridge(this, serverSocket.accept())
                    } catch (e: IOException) {
                        println("TCPConnection exception: $e")
                    }
                }
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}