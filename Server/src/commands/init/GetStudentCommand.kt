package commands.init

import commands.Command
import network.Message
import network.Message.Companion.makeMessageWithBytes

class GetStudentCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            return makeMessageWithBytes("setStudents", serializableStudents.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while getting objects: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}