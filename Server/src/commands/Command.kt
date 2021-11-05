package commands

import dao.serializable.SerializableAuth
import dao.serializable.SerializableStudent
import network.Message

abstract class Command {
    protected val serializableStudents = SerializableStudent()
    protected val serializableAuth = SerializableAuth()
    abstract fun execute(message: Message?): Message?
}