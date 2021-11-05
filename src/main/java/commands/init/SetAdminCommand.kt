package commands.init

import commands.Command
import controllers.ValidationController
import entity.user.User
import network.Message
import network.Message.Companion.makeObjectFromMessage
import java.lang.System.out

class SetAdminCommand : Command() {
    override fun execute(message: Message?) {
        out.printf("%s - action completed successfully%n", message!!.name)
        ValidationController.userList = makeObjectFromMessage(message) as ArrayList<User>?
    }
}