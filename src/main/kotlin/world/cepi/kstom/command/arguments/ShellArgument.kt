package world.cepi.kstom.command.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument

object ShellArgument : Argument<Unit>("shell") {
    override fun toString() = "Shell"
    override fun parse(sender: CommandSender, input: String) {
        TODO("Not yet implemented")
    }

    override fun parser(): String {
        TODO("Not yet implemented")
    }
}