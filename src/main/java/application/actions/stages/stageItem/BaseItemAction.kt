package application.actions.stages.stageItem

import application.actions.ActionFactory
import controllers.StudentController
import controllers.ValidationController

open class BaseItemAction {
    val actionFactory: ActionFactory = ActionFactory.instance
    val studentController = StudentController()
    val validationController = ValidationController()
}