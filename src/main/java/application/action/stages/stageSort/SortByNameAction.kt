package application.action.stages.stageSort

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class SortByNameAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Sorting by Name Student"
    }

    override fun action() {
        studentController.sortByStringObject()
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}