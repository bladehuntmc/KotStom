package net.bladehunt.kotstom.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.entity.Player

/**
 * Context for a `Kommand`'s executor
 *
 * @property sender The sender of the command
 * @property context The command context
 * @property commandName The name of the command
 * @property player The sender as a player. This will throw a `ClassCastException` if the sender
 *   isn't a player
 * @author oglassdev
 */
data class KommandExecutorContext(val sender: CommandSender, val context: CommandContext) {
    val commandName = context.commandName
    val player: Player
        get() = sender as Player

    /**
     * An indexing operator that retrieves the value of an argument
     *
     * @param argument The argument to retrieve
     * @author oglassdev
     */
    operator fun <T> get(argument: Argument<T>): T = context[argument]

    /**
     * Retrieves the value of an argument from the `CommandContext`
     *
     * @author oglassdev
     */
    operator fun <T> Argument<T>.invoke(): T = context[this]
}
