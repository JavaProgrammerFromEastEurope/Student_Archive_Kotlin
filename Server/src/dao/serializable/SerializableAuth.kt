package dao.serializable

import dao.IUser
import entity.user.User
import java.io.*
import java.util.*

class SerializableAuth : IUser {
    override fun get(): MutableList<User> {
        try {
            javaClass.getResourceAsStream(String.format("/%s", PATH)).use { stream ->
                val ios = ObjectInputStream(stream)
                return ios.readObject() as MutableList<User>
            }
        } catch (e: IOException) {
            return ArrayList()
        } catch (e: ClassNotFoundException) {
            return ArrayList()
        }
    }

    override fun save(students: List<User>) {
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
        private const val PATH = "resources/validation.bin"
    }
}