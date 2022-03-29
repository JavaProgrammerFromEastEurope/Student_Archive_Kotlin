package dao.serializable

import dao.StudentService
import entity.student.Student
import java.io.*
import java.util.*

class SerializableStudent : StudentService {
    override fun get(): MutableList<Student> {
        try {
            javaClass.getResourceAsStream(String.format("/%s", PATH)).use { stream ->
                val ios = ObjectInputStream(stream)
                return ios.readObject() as MutableList<Student>
            }
        } catch (e: IOException) {
            return ArrayList()
        } catch (e: ClassNotFoundException) {
            return ArrayList()
        }
    }

    override fun save(students: List<Student>) {
        try {
            val classLoader = javaClass.classLoader
            val file = File(Objects.requireNonNull(classLoader.getResource(PATH)).file)
            val oos = ObjectOutputStream(FileOutputStream(file))
            oos.writeObject(students)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val PATH = "resources/students.bin"
    }
}