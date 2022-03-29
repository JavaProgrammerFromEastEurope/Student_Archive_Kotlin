package application.action

import application.exception.StopApplicationException

interface IAction {
    fun description(): String

    @Throws(StopApplicationException::class)
    fun action()
}