package commands.init

import commands.Command
import network.Message
import network.Message.Companion.makeMessageWithBytes

class GetAdminCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            return makeMessageWithBytes("setAdmin", serializableAuth.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while getting users: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}