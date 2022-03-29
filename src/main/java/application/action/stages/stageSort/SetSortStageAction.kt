package application.action.stages.stageSort

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class SetSortStageAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Show List Of Students"
    }

    override fun action() {
        actionFactory.setCurrentStage(actionFactory.sortStage)
    }
}