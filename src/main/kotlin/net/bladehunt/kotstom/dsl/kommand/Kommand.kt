package net.bladehunt.kotstom.dsl.kommand

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.command.Kommand
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.bladehunt.kotstom.coroutines.MinestomDispatcher
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import kotlin.coroutines.CoroutineContext

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class KommandDSL

/**
 * Sets the default executor of the Kommand
 *
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutor(crossinline block: @KommandDSL KommandExecutorContext.() -> Unit) =
    CommandExecutor { sender, context -> KommandExecutorContext(sender, context).block() }
        .also { defaultExecutor = it }

/**
 * Sets the async default executor of the Kommand
 *
 * @param context The `CoroutineContext` to launch the coroutine in
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutorAsync(
    context: CoroutineContext = MinestomDispatcher,
    crossinline block: @KommandDSL suspend KommandExecutorContext.() -> Unit
) =
    CommandExecutor { sender, ctx ->
        CoroutineScope(context).launch { KommandExecutorContext(sender, ctx).block() }
    }
        .also { defaultExecutor = it }

/**
 * Sets the blocking default executor of the Kommand
 *
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutorBlocking(
    crossinline block: @KommandDSL suspend KommandExecutorContext.() -> Unit
) =
    CommandExecutor { sender, ctx ->
        runBlocking { KommandExecutorContext(sender, ctx).block() }
    }
        .also { defaultExecutor = it }

/**
 * Creates a subcommand using another `Kommand` builder
 *
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.subkommand(name: String, vararg aliases: String, block: @KommandDSL Kommand.() -> Unit): Kommand =
    Kommand(name, *aliases).apply(block).apply(subcommands::add)

/**
 * Adds a subcommand to the command's subcommand
 *
 * @param command The subcommand to add
 * @author oglassdev
 */
@KommandDSL
fun Kommand.subcommand(command: Command) = subcommands.add(command)

/**
 * `Kommand` builder DSL
 *
 * @see net.bladehunt.kotstom.command.Kommand
 * @author oglassdev
 */
@KommandDSL
inline fun kommand(name: String, vararg aliases: String, block: @KommandDSL Kommand.() -> Unit): Kommand =
    Kommand(name, *aliases).apply(block)