package net.bladehunt.kotstom.coroutines.kommand

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.command.Kommand
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.bladehunt.kotstom.coroutines.MinestomDispatcher
import net.bladehunt.kotstom.dsl.kommand.KommandDSL
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import kotlin.coroutines.CoroutineContext

internal typealias SuspendSingleKommandExecutor = suspend KommandExecutorContext.(CommandSender) -> Unit

internal typealias SuspendKommandExecutor = suspend KommandExecutorContext.(CommandSender, CommandContext) -> Unit

/**
 * Sets the async default executor of the Kommand
 *
 * @param context The `CoroutineContext` to launch the coroutine in
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutorAsync(
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    crossinline block: @KommandDSL SuspendSingleKommandExecutor
) = defaultExecutorAsync { sender, _ -> block(sender) }

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
    crossinline block: @KommandDSL SuspendKommandExecutor
) =
    CommandExecutor { sender, ctx ->
        CoroutineScope(context).launch { KommandExecutorContext(ctx).block(sender, ctx) }
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
    coroutineContext: CoroutineContext = Dispatchers.Default,
    crossinline block: @KommandDSL SuspendSingleKommandExecutor
) = defaultExecutorBlocking(coroutineContext) { sender, _ -> block(sender) }

/**
 * Sets the blocking default executor of the Kommand
 *
 * @param block The suspending code to run
 * @author oglassdev
 */
@KommandDSL
inline fun Kommand.defaultExecutorBlocking(
    coroutineContext: CoroutineContext = Dispatchers.Default,
    crossinline block: @KommandDSL SuspendKommandExecutor
) =
    CommandExecutor { sender, ctx ->
        runBlocking(coroutineContext) { KommandExecutorContext(ctx).block(sender, ctx) }
    }
        .also { defaultExecutor = it }