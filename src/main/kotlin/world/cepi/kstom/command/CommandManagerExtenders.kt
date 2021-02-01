package world.cepi.kstom.command

import net.minestom.server.command.CommandManager
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Arguments
import net.minestom.server.command.builder.Command

public inline fun CommandManager.command(name: String, block: Command.() -> Unit): Unit =
    register(Command(name).apply(block))

public fun CommandManager.register(name: String, block: (sender: CommandSender, args: Arguments) -> Unit): Unit =
    register(Command(name).apply { default(block) })