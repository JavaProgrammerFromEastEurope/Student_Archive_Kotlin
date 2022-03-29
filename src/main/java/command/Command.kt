package command

import network.Message

abstract class Command {
    abstract fun execute(message: Message?)
}