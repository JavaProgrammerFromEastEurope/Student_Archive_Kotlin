package dao

import application.exception.NotFoundItemException
import entity.student.Student

interface StudentService {
    fun get(): MutableList<Student>
    fun add(newStudent: Student) {
        val students = get()
        for (student in students) {
            if (student.id == newStudent.id) {
                (students[students.size - 1].id + 1)
                    .also { newStudent.id = it }
            }
        }
        students.add(newStudent)
        save(students)
    }

    @Throws(NotFoundItemException::class)
    fun update(newStudent: Student) {
        val students = get()
        students.withIndex().forEach { (index, student) ->
            if (student.id == newStudent.id) {
                students[index] = newStudent
                save(students)
                return
            }
        }
    }

    @Throws(NotFoundItemException::class)
    fun remove(removeID: Long) {
        val students = get()
        for (student in students) {
            if (student.id == removeID) {
                students.remove(student)
                save(students)
                return
            }
        }
    }

    fun save(students: List<Student>)
}