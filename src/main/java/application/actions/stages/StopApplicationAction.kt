package application.actions.stages

import application.actions.IAction
import application.exceptions.StopApplicationException
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