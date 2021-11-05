package application.exceptions

class NotFoundItemException(private val id: Long) : Exception() {
    override val message: String
        get() = String.format("Not found item with id = %d", id)
}