package application.action.stages.stageItem

import application.action.IAction

class UpdateStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Update Student"
    }

    override fun action() {
        studentController.updateObject()
    }
}