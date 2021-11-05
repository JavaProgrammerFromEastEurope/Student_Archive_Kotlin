import application.Application
import receiver.Connection

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Connection.connect()
        Application.startEntertainment()
    }
}