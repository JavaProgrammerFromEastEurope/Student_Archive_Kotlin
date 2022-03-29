package application.action.stages.stageSort

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class SortByIdAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Sorting by Id Student"
    }

    override fun action() {
        studentController.sortByIntObject()
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}