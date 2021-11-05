package application.actions.stages.stageItem

import application.actions.IAction

class UpdateStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Update Student"
    }

    override fun action() {
        studentController.updateObject()
    }
}