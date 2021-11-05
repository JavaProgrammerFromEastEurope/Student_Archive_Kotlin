package application.actions.stages.stageItem

import application.actions.IAction

class AddStudentAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Add New Student"
    }

    override fun action() {
        studentController.addObject()
    }
}