package application.actions.stages.stageSort

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class SetSortStageAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Show List Of Students"
    }

    override fun action() {
        actionFactory.setCurrentStage(actionFactory.sortStage)
    }
}