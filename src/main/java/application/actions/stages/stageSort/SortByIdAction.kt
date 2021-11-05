package application.actions.stages.stageSort

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class SortByIdAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Sorting by Id Student"
    }

    override fun action() {
        studentController.sortByIntObject()
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}