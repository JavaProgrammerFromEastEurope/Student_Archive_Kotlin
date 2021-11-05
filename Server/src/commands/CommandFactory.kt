package commands

import application.exceptions.NotFoundCommandException
import commands.init.*

class CommandFactory private constructor() {
    private val commands = HashMap<String, Command>()
    @Throws(NotFoundCommandException::class)
    fun getCommand(commandName: String): Command? {
        if (commands.containsKey(commandName)) {
            return commands[commandName]
        }
        throw NotFoundCommandException(commandName)
    }

    companion object {
        val instance = CommandFactory()
    }

    init {
        commands["addAdmin"] = AddAdminCommand()
        commands["getAdmin"] = GetAdminCommand()
        commands["add"] = AddStudentCommand()
        commands["get"] = GetStudentCommand()
        commands["update"] = UpdateStudentCommand()
        commands["remove"] = RemoveStudentCommand()
    }
}