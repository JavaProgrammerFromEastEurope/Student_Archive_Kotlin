package application.exception

class WrongStageException(private val length: Int) : Exception() {
    override val message: String
        get() {
            val character = 122891
            return String.format(
                "Don't be a stranger, world is beautiful %c  ! %d - is Wrong!",
                character.toChar(),
                length
            )
        }
}