package application.action.stages.stageItem

import application.action.ActionFactory
import controller.StudentController
import controller.ValidationController

open class BaseItemAction {
    val actionFactory: ActionFactory = ActionFactory.instance
    val studentController = StudentController()
    val validationController = ValidationController()
}