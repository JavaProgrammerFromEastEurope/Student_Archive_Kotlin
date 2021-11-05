package application.actions.stages.stageAuth

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class GetAdminAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Get Admin"
    }

    override fun action() {
        validationController.admin
    }
}