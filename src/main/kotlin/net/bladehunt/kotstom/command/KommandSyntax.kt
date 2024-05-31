package net.bladehunt.kotstom.command

import java.util.function.Function
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.condition.CommandCondition

class KommandSyntax(
    commandCondition: CommandCondition?,
    commandExecutor: CommandExecutor,
    vararg args: Argument<*>,
    defaultValues: Map<String, Function<CommandSender, Any?>> = emptyMap(),
) : CommandSyntax(commandCondition, commandExecutor, defaultValues, *args) {
    override fun toString(): String {
        return "KommandSyntax(defaultValuesMap=$defaultValuesMap,arguments=$arguments)"
    }
}
