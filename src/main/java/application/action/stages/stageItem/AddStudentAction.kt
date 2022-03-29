package application.action.stages.stageItem

import application.action.IAction

class AddStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Add New Student"
    }

    override fun action() {
        studentController.addObject()
    }
}