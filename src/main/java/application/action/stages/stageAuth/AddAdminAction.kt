package application.action.stages.stageAuth

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class AddAdminAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Add New Administrator"
    }

    override fun action() {
        validationController.addUser()
    }
}