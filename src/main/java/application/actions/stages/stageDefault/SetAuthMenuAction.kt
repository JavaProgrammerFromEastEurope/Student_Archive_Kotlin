package application.actions.stages.stageDefault

import application.actions.ActionFactory
import application.actions.IAction

class SetAuthMenuAction : IAction {
    private val actionFactory: ActionFactory = ActionFactory.instance
    override fun description(): String {
        return "Return to Main Menu"
    }

    override fun action() {
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}