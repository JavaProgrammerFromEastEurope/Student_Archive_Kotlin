package application.exceptions

class StopApplicationException : Exception() {
    override val message: String
        get() = "The application has finished it's work!"
}