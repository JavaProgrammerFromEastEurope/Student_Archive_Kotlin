package application.actions.stages.stageItem

import application.actions.IAction

class RemoveStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Remove Student From List"
    }

    override fun action() {
        studentController.removeObject()
    }
}