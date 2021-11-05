package application.sortComparator.user

import application.sortComparator.ISortTemplate
import entity.user.User

class SortByIdAdmin : ISortTemplate<User> {
    override fun compare(o1: User, o2: User): Int {
        return (o1.id - o2.id).toInt()
    }
}