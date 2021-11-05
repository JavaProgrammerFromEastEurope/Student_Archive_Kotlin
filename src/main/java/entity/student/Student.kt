package entity.student

import java.io.Serializable
import java.util.*

class Student(
    val firstName: String,
    private val lastName: String,
    private val dateOfBirth: GregorianCalendar,
    private val education: Education,
    private val groupNumber: Int
) : Person(firstName, lastName, dateOfBirth), Serializable {
    var id = 0L
    private var exams: List<Exam> = ArrayList()

    enum class Education {
        Specialist, Bachelor, SecondEducation;

        companion object {
            fun valueOf(i: Int): Education {
                when (i) {
                    1 -> return Specialist
                    2 -> return Bachelor
                    3 -> return SecondEducation
                }
                return Specialist
            }
        }
    }

    fun setExams(params: ArrayList<Exam>) {
        exams = params
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        other as Student
        return  (education != other.education) &&
            (groupNumber != other.groupNumber) &&
        (id != other.id) && (exams != other.exams)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            super.hashCode(), id, firstName, lastName,
            dateOfBirth, education, groupNumber, exams)
    }

    override fun toString(): String {
        return "Student( id=$id, " +
                "first name=$firstName, " +
                "last name=$lastName, " +
                "education=$education, " +
                "date Of birth=${dateOfBirth.time}, " +
                "groupNumber=$groupNumber," +
                " exams=${listOf(exams)}"
    }
}