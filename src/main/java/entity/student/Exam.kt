package entity.student

import java.io.Serializable
import java.util.*

class Exam(
    private val subjectName: String,
    private val score: Int,
    private val examDate: GregorianCalendar
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val exam = other as Exam
        return (subjectName == exam.subjectName
                && score == exam.score
                && examDate == exam.examDate)
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), subjectName, score, examDate)
    }

    override fun toString(): String {
        return "Exam{" +
                "subjectName='" + subjectName + '\'' +
                ", score=" + score +
                ", examDate=" + examDate.time +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}