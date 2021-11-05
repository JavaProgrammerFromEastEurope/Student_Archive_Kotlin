package controllers

import application.actions.ActionFactory
import application.exceptions.StopApplicationException
import application.utils.Input
import entity.user.User
import network.Message.Companion.create
import network.Message.Companion.makeMessageWithBytes
import receiver.Receiver
import utility.SecurityUtility

class ValidationController {
    private fun createUser(): User? {
        return initUser()
    }

    private fun initUser(): User? {
        println("Input user information:")
        val login = Input.getString("Input login:")
        val pass = inputPasswords() ?: return null
        return User(login, pass)
    }

    private fun inputPasswords(): String? {
        for (i in 0..1) {
            val first = Input.getString("Password:")
            val second = Input.getString("Try again:")
            if (first == second) return first
            println("Entered passwords aren't equal!")
        }
        println("Passwords are mismatch.\n Cancel creating user!")
        return null
    }

    fun addUser() {
        val user = createUser()
        if (user == null) {
            println("Abort creating admin user!")
            return
        }
        user.password = SecurityUtility.passwordEncoder().encode(user.password)
        Receiver.communicationBridge!!
            .sendMessage(makeMessageWithBytes("addAdmin", user))
    }

    val admin: Unit
        get() {
            Receiver.communicationBridge!!
                .sendMessage(create("getAdmin"))
        }

    fun authorisedAction() {
        val actionFactory: ActionFactory = ActionFactory.instance
        val currentUser = initUser()
        for (user in userList!!) {
            if (SecurityUtility.passwordEncoder().matches(currentUser!!.password, user.password)) {
                if (user.userName == currentUser.userName) {
                    println("Access Granted!")
                    try {
                        actionFactory.sortStage[1]!!.action()
                    } catch (e: StopApplicationException) {
                        e.printStackTrace()
                    }
                    actionFactory.setCurrentStage(actionFactory.authStage)
                    return
                }
            }
        }
        println("Please check authentication data and try again!")
    }

    companion object {
        var userList: ArrayList<User>? = ArrayList()
    }
}