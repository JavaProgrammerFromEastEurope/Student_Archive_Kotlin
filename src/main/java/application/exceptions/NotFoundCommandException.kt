package application.exceptions

class NotFoundCommandException(private val commandName: String) : Exception() {
    override val message: String
        get() = String.format("Not found command with name = %s", commandName)
}