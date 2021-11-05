package commands

import application.exceptions.NotFoundCommandException
import commands.init.SetAdminCommand
import commands.init.SetStudentsCommand

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
        commands["addAdmin"] = SetAdminCommand()
        commands["setAdmin"] = SetAdminCommand()
        commands["setStudents"] = SetStudentsCommand()
    }
}