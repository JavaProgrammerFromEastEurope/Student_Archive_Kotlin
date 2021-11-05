package application.actions.stages.stageAuth

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class AddAdminAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Add New Administrator"
    }

    override fun action() {
        validationController.addUser()
    }
}