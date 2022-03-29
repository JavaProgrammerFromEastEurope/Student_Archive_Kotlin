package application.action.stages.stageDefault

import application.action.ActionFactory
import application.action.IAction

class SetDefaultMenuAction : IAction {
    private val actionFactory: ActionFactory = ActionFactory.instance
    override fun description(): String {
        return "Return to Main Menu"
    }

    override fun action() {
        actionFactory.setCurrentStage(actionFactory.defaultStage)
    }
}