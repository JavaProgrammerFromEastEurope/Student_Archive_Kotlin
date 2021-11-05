package application.sortComparator.student

import application.sortComparator.ISortTemplate
import entity.student.Student

class SortByNameStudent : ISortTemplate<Student> {
    override fun compare(o1: Student, o2: Student): Int {
        return o1.firstName.compareTo(o2.firstName)
    }
}