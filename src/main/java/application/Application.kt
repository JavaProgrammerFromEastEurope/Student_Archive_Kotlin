package application

import application.action.ActionFactory
import application.action.IAction
import application.action.stages.stageAuth.GetAdminAction
import application.exception.StopApplicationException
import application.exception.WrongStageException
import application.util.Input

object Application {
    private val stageFactory: ActionFactory = ActionFactory.instance
    fun startEntertainment() {
        println("Welcome! Entertainment has become!")
        actionStages()
    }

    private fun actionStages() {
        stageFactory.addDefaultStageAction()
        GetAdminAction().action()
        while (true) try {
            actionStage!!.action()
        } catch (e: StopApplicationException) {
            e.message
            return
        }
    }

    private val actionStage: IAction?
        get() {
            stageFactory.showStageMenu()
            val index = Input.integer
            try {
                return stageFactory.getActionStage(index)
            } catch (e: WrongStageException) {
                e.printStackTrace()
            }
            return actionStage
        }
}