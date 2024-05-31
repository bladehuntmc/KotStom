package net.bladehunt.kotstom.dsl.kommand

import java.util.function.Function
import net.bladehunt.kotstom.command.KommandConditionContext
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.bladehunt.kotstom.command.KommandSyntax
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.condition.CommandCondition
import net.minestom.server.entity.Player

data class SyntaxBuilder(
    val conditions: MutableList<CommandCondition> = arrayListOf(),
    var executor: CommandExecutor? = null,
    val defaultValues: MutableMap<String, Function<CommandSender, Any?>> = hashMapOf(),
    val arguments: MutableList<Argument<*>> = arrayListOf()
) {
    @KommandDSL
    fun onlyPlayers(): CommandCondition =
        CommandCondition { commandSender, _ -> commandSender is Player }.also { conditions += it }

    @KommandDSL
    inline fun condition(
        crossinline block: @KommandDSL KommandConditionContext.() -> Boolean
    ): CommandCondition =
        CommandCondition { commandSender, input ->
                KommandConditionContext(commandSender, input).block()
            }
            .also { conditions += it }

    @KommandDSL
    inline fun executor(
        crossinline block: @KommandDSL KommandExecutorContext.() -> Unit
    ): CommandExecutor =
        CommandExecutor { commandSender, commandContext ->
                KommandExecutorContext(commandSender, commandContext).block()
            }
            .also { executor = it }

    @KommandDSL
    inline fun default(
        key: String,
        crossinline block: @KommandDSL (CommandSender) -> Any?
    ): Function<CommandSender, Any?> =
        Function<CommandSender, Any?> { sender -> block(sender) }.also { defaultValues[key] = it }

    fun build(): CommandSyntax {
        if (executor == null) throw IllegalArgumentException("Executor cannot be null!")
        return KommandSyntax(
            commandCondition =
                when (conditions.size) {
                    1 -> conditions.first()
                    /* Automagically returns true if nothing exists */
                    else ->
                        CommandCondition { sender, label ->
                            conditions.all { it.canUse(sender, label) }
                        }
                },
            commandExecutor = executor!!,
            defaultValues = defaultValues,
            args = arguments.toTypedArray()
        )
    }
}

@KommandDSL
inline fun KommandBuilder.syntax(
    vararg arguments: Argument<*>,
    crossinline condition: @KommandDSL (sender: CommandSender, label: String?) -> Boolean =
        { _, _ ->
            true
        },
    defaultValues: Map<String, Function<CommandSender, Any?>> = emptyMap(),
    crossinline block: @KommandDSL KommandExecutorContext.() -> Unit,
) =
    KommandSyntax(
            commandCondition = { sender, label -> condition(sender, label) },
            commandExecutor = { sender, context ->
                KommandExecutorContext(sender, context).block()
            },
            defaultValues = defaultValues,
            args = arguments
        )
        .let { syntaxes.add(it) }

@KommandDSL
inline fun KommandBuilder.buildSyntax(
    vararg arguments: Argument<*>,
    block: @KommandDSL SyntaxBuilder.() -> Unit
) =
    SyntaxBuilder(arguments = arguments.toMutableList()).let {
        it.block()
        syntaxes.add(it.build())
    }

@KommandDSL
inline fun KommandBuilder.buildSyntax(block: @KommandDSL SyntaxBuilder.() -> Unit) =
    SyntaxBuilder().let {
        it.block()
        syntaxes.add(it.build())
    }
