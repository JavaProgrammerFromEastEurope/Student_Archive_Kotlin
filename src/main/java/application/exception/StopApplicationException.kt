package application.exception

class StopApplicationException : Exception() {
    override val message: String
        get() = "The application has finished it's work!"
}