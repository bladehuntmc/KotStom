package net.bladehunt.kotstom.dsl.kommand

import net.bladehunt.kotstom.command.Kommand
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor

internal typealias SingleKommandExecutor = KommandExecutorContext.(CommandSender) -> Unit

internal typealias KommandExecutor = KommandExecutorContext.(CommandSender, CommandContext) -> Unit

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class KommandDSL

/**
 * Sets the default executor of the Kommand
 *
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutor(crossinline block: @KommandDSL SingleKommandExecutor) =
    defaultExecutor { sender, _ -> block(sender) }

/**
 * Sets the default executor of the Kommand
 *
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutor(crossinline block: @KommandDSL KommandExecutor) =
    CommandExecutor { sender, context -> KommandExecutorContext(context).block(sender, context) }
        .also { defaultExecutor = it }

/**
 * Creates a subcommand using another `Kommand` builder
 *
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.subkommand(name: String, vararg aliases: String, block: @KommandDSL Kommand.() -> Unit): Kommand =
    Kommand(name, *aliases).apply(block).also { command ->
        addSubcommand(command)
    }

/**
 * `Kommand` builder DSL
 *
 * @see net.bladehunt.kotstom.command.Kommand
 * @author oglassdev
 */
@KommandDSL
inline fun kommand(name: String, vararg aliases: String, block: @KommandDSL Kommand.() -> Unit): Kommand =
    Kommand(name, *aliases).apply(block)