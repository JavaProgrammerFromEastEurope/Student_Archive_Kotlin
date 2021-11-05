package application.actions

import application.actions.stages.StopApplicationAction
import application.actions.stages.stageAuth.AddAdminAction
import application.actions.stages.stageAuth.SetAuthStageAction
import application.actions.stages.stageDefault.SetAuthMenuAction
import application.actions.stages.stageItem.AddStudentAction
import application.actions.stages.stageItem.RemoveStudentAction
import application.actions.stages.stageItem.UpdateStudentAction
import application.actions.stages.stageSort.SetSortStageAction
import application.actions.stages.stageSort.SortByIdAction
import application.actions.stages.stageSort.SortByNameAction
import application.exceptions.WrongStageException

class ActionFactory private constructor() {
    val authStage = HashMap<Int, IAction>()
    val defaultStage = HashMap<Int, IAction>()
    val sortStage = HashMap<Int, IAction>()
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