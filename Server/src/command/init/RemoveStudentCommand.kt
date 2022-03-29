package command.init

import command.Command
import network.Message
import network.Message.Companion.makeMessageWithBytes
import network.Message.Companion.makeObjectFromMessage

class RemoveStudentCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            val id = makeObjectFromMessage(message!!) as Long
            serializableStudents.remove(id)
            return makeMessageWithBytes("setStudents", serializableStudents.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while removing students: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}