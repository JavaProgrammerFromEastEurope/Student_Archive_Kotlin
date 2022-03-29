package command.init

import command.Command
import controller.StudentController
import entity.student.Student
import network.Message
import network.Message.Companion.makeObjectFromMessage
import java.lang.System.out

class SetStudentsCommand : Command() {
    override fun execute(message: Message?) {
        out.printf("%s - action completed successfully%n", message!!.name)
        StudentController.studentList = makeObjectFromMessage(message) as ArrayList<Student>
    }
}