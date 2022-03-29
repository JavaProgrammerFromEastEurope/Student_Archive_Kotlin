package application.action.stages

import application.action.IAction
import application.exception.StopApplicationException
import receiver.Receiver

class StopApplicationAction : IAction {
    override fun description(): String {
        return "Exit From Application"
    }

    @Throws(StopApplicationException::class)
    override fun action() {
        Receiver.communicationBridge!!.disconnect()
        throw StopApplicationException()
    }
}