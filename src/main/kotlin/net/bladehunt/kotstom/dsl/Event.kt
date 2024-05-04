package net.bladehunt.kotstom.dsl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import kotlin.coroutines.CoroutineContext

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class EventDSL

data class EventBuilder<T : Event>(val clazz: Class<T>) : EventListener.Builder<T>(clazz) {
    var expireCount: Int
        get() = throw IllegalAccessError("Expire count can't be accessed in the context of a builder.")
        set(value) {
            expireCount(value)
        }
    val filters = Filters()

    fun suspendingHandler(context: CoroutineContext = Dispatchers.Default, lambda: suspend T.() -> Unit) = handler {
        CoroutineScope(context).launch {
            lambda(it)
        }
    }

    inner class Filters {
        operator fun plusAssign(lambda: (T) -> Boolean) {
            filter(lambda)
        }
    }
}
inline fun <reified E : Event> EventNode<in E>.builder(block: @EventDSL EventBuilder<E>.() -> Unit): EventNode<in E> = addListener(
    EventBuilder(E::class.java).apply(block).build()
)
inline fun <reified E : Event> EventNode<in E>.listen(noinline block: (E) -> Unit): EventNode<in E> = addListener(
    E::class.java,
    block
)