package receiver

import application.exceptions.NotFoundCommandException
import commands.CommandFactory
import network.CommunicationBridge
import network.ITCPConnectionListener
import network.Message

class Receiver(
    private val ipAddress: String?,
    private val port: Int
) : ITCPConnectionListener {
    fun run(): Boolean {
        return try {
            communicationBridge = CommunicationBridge(this, ipAddress, port)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun onConnectionReady(tcpConnection: CommunicationBridge?) {
        println("Connection Established")
    }

    override fun onReceiveString(tcpConnection: CommunicationBridge?, value: String?) {
        println(value)
    }

    override fun onReceiveMessage(tcpConnection: CommunicationBridge?, message: Message?) {
        asyncReadMessages(message)
        Connection.connect()
    }

    @Synchronized
    private fun asyncReadMessages(message: Message?) {
        if (message!!.name == "exception") {
            System.out.printf("Server return exception:\n %s", listOf(message.data))
        } else {
            try {
                CommandFactory.instance.getCommand(message.name)!!.execute(message)
            } catch (e: NotFoundCommandException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDisconnect(tcpConnection: CommunicationBridge?) {
        println("Connection Close")
    }

    override fun onException(tcpConnection: CommunicationBridge?, e: Exception?) {
        //out.println(e.getMessage());
    }

    companion object {
        var communicationBridge: CommunicationBridge? = null
    }
}