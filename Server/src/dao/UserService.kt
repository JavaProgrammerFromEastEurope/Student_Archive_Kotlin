package dao

import entity.user.User

interface UserService {
    fun get(): MutableList<User>
    fun add(newStudent: User) {
        val students = get()
        for (student in students) {
            if (student.id == newStudent.id) {
                newStudent.id = students.size.toLong()
            }
        }
        students.add(newStudent)
        save(students)
    }

    fun save(flowerCompositions: List<User>)
}