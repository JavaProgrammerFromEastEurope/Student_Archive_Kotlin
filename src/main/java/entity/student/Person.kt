package entity.student

import java.io.Serializable
import java.util.*

open class Person(
    private val firstName: String,
    private val lastName: String,
    private val dateOfBirth: GregorianCalendar
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val person = other as Person
        return (firstName == person.firstName
                && lastName == person.lastName
                && dateOfBirth == person.dateOfBirth)
    }
    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), firstName, lastName, dateOfBirth)
    }

    override fun toString(): String {
        return "Person(" +
                "firstName='$firstName'," +
                " lastName='$lastName'," +
                " dateOfBirth=$dateOfBirth)"
    }
}