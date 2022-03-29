package command.impl

import command.Command
import entity.student.Student
import network.Message
import network.Message.Companion.makeMessageWithBytes
import network.Message.Companion.makeObjectFromMessage

class AddStudentCommand : Command() {
    override fun execute(message: Message?): Message? {
        try {
            val student = makeObjectFromMessage(message!!) as Student?
            serializableStudents.add(student!!)
            return makeMessageWithBytes("setStudents", serializableStudents.get())
        } catch (e: Exception) {
            makeMessageWithBytes(
                "exception", String.format("Exception while adding objects: %s", e.message)
            )
        }
        throw NullPointerException()
    }
}