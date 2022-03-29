package controller

import application.sortComparator.ISortTemplate
import application.sortComparator.student.SortByIdStudent
import application.sortComparator.student.SortByNameStudent
import application.util.Input
import entity.student.Exam
import entity.student.Student
import entity.student.Student.Education
import network.Message.Companion.create
import network.Message.Companion.makeMessageWithBytes
import receiver.Receiver
import java.lang.System.out
import java.util.*

class StudentController : ControllerTemplate {
    private fun createStudent(): Student {
        val student = initStudent()
        student.setExams(initExams())
        return student
    }

    private fun initStudent(): Student {
        println("Input student information:")
        out.printf(
            "Choose the education: -> %n 1 - Specialist%n " +
                    "2 - Bachelor%n 3 - SecondEducation%n"
        )
        val education: Education = Education.Companion.valueOf(Input.inputPosition(3))
        return Student(
            Input.getString("Input first name of student"),
            Input.getString("Input second name of student"),
            GregorianCalendar(
                Input.inputPosition("Year of birth", 2011),
                Input.inputPosition("Month of birth", 12),
                Input.inputPosition("Day of birth", 31)
            ),
            education, Input.getInteger("Number of group")
        )
    }

    private fun initExams(): ArrayList<Exam> {
        println("Input quantity of exams:")
        val exams = ArrayList<Exam>()
        val quantity = Input.integer
        for (i in 0 until quantity) {
            out.printf("Input %d exam:%n", i + 1)
            exams.add(
                i,
                Exam(
                    Input.getString("Name of exam:"),
                    Input.inputPosition("Score:", 10),
                    GregorianCalendar()
                )
            )
        }
        return exams
    }

    override fun addObject() {
        Receiver.communicationBridge!!
            .sendMessage(makeMessageWithBytes("add", createStudent()))
        //.sendString("OK");
    }

    override val objects: Unit
        get() {
            Receiver.communicationBridge!!
                .sendMessage(create("get"))
        }

    override fun updateObject() {
        val id = Input.getInteger("Input student id to update:").toLong()
        if (showAcceptTemplate(id, "update")) {
            return
        }
        val student = createStudent()
        student.id = id
        Receiver.communicationBridge!!
            .sendMessage(makeMessageWithBytes("update", student))
    }

    override fun removeObject() {
        val id = Input.getInteger("Input student id to remove:").toLong()
        if (showAcceptTemplate(id, "remove")) {
            return
        }
        Receiver.communicationBridge!!
            .sendMessage(makeMessageWithBytes("remove", id))
    }

    private fun showAcceptTemplate(id: Long, text: String?): Boolean {
        if (id > studentList!![studentList!!.size - 1].id) {
            out.printf(
                "Inputted Id is wrong to %s! %n" +
                        "Cancel to %s student %n", text, id
            )
            return true
        }
        out.printf(
            "Are you really wanna to %s current %sth student?%n" +
                    " Tape 'y' to accept.%n", text, id + 1L
        )
        val str = Input.inputCharacter()
        if (str != "y") {
            out.printf("Cancel to %s %s student", text, id)
            return true
        }
        return false
    }

    override fun sortByStringObject() {
        checkStudentList(SortByNameStudent())
    }

    override fun sortByIntObject() {
        checkStudentList(SortByIdStudent())
    }

    private fun checkStudentList(sort: ISortTemplate<Student>) {
        if (!(studentList == null || studentList!!.size == 0)) {
            studentList!!.sortWith(sort)
            printListItems(studentList)
        } else {
            println("Student list trying initialized...")
            objects
        }
    }

    private fun printListItems(students: List<Student>?) {
        if (studentList == null || studentList!!.size == 0) {
            println("List of users is empty!")
        }
        for (student in students!!) {
            println(student)
        }
        println()
    }

    companion object {
        var studentList: ArrayList<Student>? = ArrayList()
    }
}