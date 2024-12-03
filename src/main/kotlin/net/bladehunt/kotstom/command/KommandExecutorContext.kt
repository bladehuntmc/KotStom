package net.bladehunt.kotstom.command

import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.Argument

/**
 * CommandContext wrapper that provides extension functions for Arguments
 *
 * @property context The command context
 * @author oglassdev
 */
@JvmInline
value class KommandExecutorContext(private val context: CommandContext) {
    /**
     * Retrieves the value of an argument from the `CommandContext`
     *
     * @author oglassdev
     */
    operator fun <T> Argument<T>.invoke(): T = context[this]
}
