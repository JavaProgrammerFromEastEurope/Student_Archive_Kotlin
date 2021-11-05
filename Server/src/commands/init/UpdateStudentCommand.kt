package commands.init

import commands.Command
import entity.student.Student
import network.Message
import network.Message.Companion.makeMessageWithBytes
import network.Message.Companion.makeObjectFromMessage

class UpdateStudentCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            val student = makeObjectFromMessage(message!!) as Student?
            serializableStudents.update(student!!)
            return makeMessageWithBytes("setStudents", serializableStudents.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while updating students: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}