package application.action.stages.stageDefault

import application.action.ActionFactory
import application.action.IAction

class SetAuthMenuAction : IAction {
    private val actionFactory: ActionFactory = ActionFactory.instance
    override fun description(): String {
        return "Return to Main Menu"
    }

    override fun action() {
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}