package application.action.stages.stageAuth

import application.action.IAction
import application.action.stages.stageItem.BaseItemAction

class SetAuthStageAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Admin Authorisation"
    }

    override fun action() {
        validationController.authorisedAction()
    }
}