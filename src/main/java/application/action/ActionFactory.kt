package application.action

import application.action.stages.StopApplicationAction
import application.action.stages.stageAuth.AddAdminAction
import application.action.stages.stageAuth.SetAuthStageAction
import application.action.stages.stageDefault.SetAuthMenuAction
import application.action.stages.stageItem.AddStudentAction
import application.action.stages.stageItem.RemoveStudentAction
import application.action.stages.stageItem.UpdateStudentAction
import application.action.stages.stageSort.SetSortStageAction
import application.action.stages.stageSort.SortByIdAction
import application.action.stages.stageSort.SortByNameAction
import application.exception.WrongStageException

class ActionFactory private constructor() {
    val authStage    = HashMap<Int, IAction>()
    val defaultStage = HashMap<Int, IAction>()
    val sortStage    = HashMap<Int, IAction>()
    private var currentStage = defaultStage

    @Throws(WrongStageException::class)
    fun getActionStage(index: Int): IAction {
        if (currentStage.containsKey(index)) {
            return currentStage[index]!!
        }
        throw WrongStageException(index)
    }

    fun addDefaultStageAction() {
        defaultStage[0] = StopApplicationAction()
        defaultStage[1] = SetAuthStageAction()
        //defaultStage[2] = AddAdminAction() /** (admin - admin) - default **/

        sortStage[0] = SetAuthMenuAction()
        sortStage[1] = SortByIdAction()
        sortStage[2] = SortByNameAction()

        authStage[1] = SetSortStageAction()
        authStage[0] = StopApplicationAction()
    }

    fun setCurrentStage(stage: HashMap<Int, IAction>) {
        currentStage = stage
    }

    fun setSortStage() {
        currentStage = sortStage
    }

    fun setDefaultStage() {
        currentStage = authStage
    }

    fun showStageMenu() {
        currentStage.forEach { (key: Int, value: IAction) ->
            System.out.printf(
                "%d - %s%n", key, value.description()
            )
        }
        println("Choose the action you want:")
    }

    companion object {
        val instance = ActionFactory()
    }

    init {
        authStage[2] = AddStudentAction()
        authStage[3] = UpdateStudentAction()
        authStage[4] = RemoveStudentAction()
        authStage[5] = AddAdminAction()
    }
}