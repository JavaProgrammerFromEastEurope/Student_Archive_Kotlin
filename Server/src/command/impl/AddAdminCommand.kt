package command.impl

import command.Command
import entity.user.User
import network.Message
import network.Message.Companion.makeMessageWithBytes
import network.Message.Companion.makeObjectFromMessage

class AddAdminCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            val user = makeObjectFromMessage(message!!) as User?
            serializableAuth.add(user!!)
            return makeMessageWithBytes("addAdmin", serializableAuth.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while adding users: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}