package application.action.stages.stageItem

import application.action.IAction

class RemoveStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Remove Student From List"
    }

    override fun action() {
        studentController.removeObject()
    }
}