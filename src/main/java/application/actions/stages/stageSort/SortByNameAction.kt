package application.actions.stages.stageSort

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class SortByNameAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Sorting by Name Student"
    }

    override fun action() {
        studentController.sortByStringObject()
        actionFactory.setCurrentStage(actionFactory.authStage)
    }
}