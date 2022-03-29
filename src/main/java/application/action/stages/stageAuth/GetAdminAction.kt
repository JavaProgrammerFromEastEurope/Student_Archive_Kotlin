package application.action.stages.stageAuth

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class GetAdminAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Get Admin"
    }

    override fun action() {
        validationController.admin
    }
}