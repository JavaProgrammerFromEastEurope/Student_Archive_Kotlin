package application.actions

import application.exceptions.StopApplicationException

interface IAction {
    fun description(): String

    @Throws(StopApplicationException::class)
    fun action()
}