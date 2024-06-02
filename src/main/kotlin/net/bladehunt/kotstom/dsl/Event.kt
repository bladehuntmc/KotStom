package net.bladehunt.kotstom.dsl

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.coroutines.MinestomDispatcher
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class EventDSL

/**
 * An `EventListener` builder
 *
 * @author oglassdev
 */
data class EventBuilder<T : Event>(val clazz: Class<T>) : EventListener.Builder<T>(clazz) {
    /**
     * The amount of times to run until expiration of the `EventListener`
     *
     * @author oglassdev
     */
    var expireCount: Int
        get() =
            throw IllegalAccessError("Expire count can't be accessed in the context of a builder.")
        set(value) {
            expireCount(value)
        }

    /**
     * A coroutine handler that blocks until completion
     *
     * @param block The suspending code to run
     * @author oglassdev
     */
    inline fun blockingHandler(crossinline block: suspend (T) -> Unit) = handler {
        runBlocking { block(it) }
    }

    /**
     * A coroutine handler that runs asynchronously using the `MinestomDispatcher`
     *
     * @param context The `CoroutineContext` to launch the coroutine in
     * @param block The suspending code to run
     * @author oglassdev
     */
    inline fun asyncHandler(
        context: CoroutineContext = MinestomDispatcher,
        crossinline block: suspend (T) -> Unit
    ) = handler { CoroutineScope(context).launch { block(it) } }
}

/**
 * A builder DSL for `EventListener`
 *
 * @param E The `Event` to listen for
 * @author oglassdev
 */
inline fun <reified E : Event> EventNode<in E>.builder(
    block: @EventDSL EventBuilder<E>.() -> Unit
): EventNode<in E> = addListener(EventBuilder(E::class.java).apply(block).build())

/**
 * Listens for an event
 *
 * @param E The `Event` to listen for
 * @param block The listener
 * @author oglassdev
 */
inline fun <reified E : Event> EventNode<in E>.listen(
    noinline block: (E) -> Unit
): EventNode<in E> = addListener(E::class.java, block)
