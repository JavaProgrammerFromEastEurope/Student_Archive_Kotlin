package receiver

import java.io.IOException
import java.lang.System.out
import java.util.*

object Connection {
    private const val PATH = "config/receiver.properties"
    private var ipAddress: String? = null
    private var port = 0
    fun connect() {
        /** Do some preparation work
         * and start the receiver */
        readPropertyFromFile()
        startReceiver()
    }

    private fun readPropertyFromFile() = try {
        val property = Properties()
        property.load(Connection::class.java.getResourceAsStream(String.format("/%s", PATH)))
        ipAddress = property.getProperty("ipAddress")
        port = property.getProperty("port").toInt()
    } catch (e: IOException) {
        println(e.message)
    }

    private fun startReceiver() {
        var trying = 0
        while (!Receiver(ipAddress, port).run()) {
            out.printf("%s try connect to server is failed!%n", ++trying)
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}