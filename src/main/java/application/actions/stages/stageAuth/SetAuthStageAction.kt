package application.actions.stages.stageAuth

import application.actions.IAction
import application.actions.stages.stageItem.BaseItemAction

class SetAuthStageAction : BaseItemAction(), IAction {
    override fun description(): String {
        return "Admin Authorisation"
    }

    override fun action() {
        validationController.authorisedAction()
    }
}