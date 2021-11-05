package application.sortComparator

interface ISortTemplate<T> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int
}