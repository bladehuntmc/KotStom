package net.bladehunt.kstom.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.entity.Player

data class KommandExecutorContext(
    val sender: CommandSender,
    val context: CommandContext
) {
    val commandName = context.commandName
    val player by lazy { sender as Player }

    operator fun <T> get(argument: Argument<T>): T = context[argument]
    operator fun <T> Argument<T>.invoke(): T = context[this]
}