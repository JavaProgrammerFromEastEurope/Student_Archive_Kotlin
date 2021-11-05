package network

interface ITCPConnectionListener {
    fun onConnectionReady(tcpConnection: CommunicationBridge?)
    fun onReceiveString(tcpConnection: CommunicationBridge?, value: String?)
    fun onReceiveMessage(tcpConnection: CommunicationBridge?, message: Message?)
    fun onDisconnect(tcpConnection: CommunicationBridge?)
    fun onException(tcpConnection: CommunicationBridge?, e: Exception?)
}