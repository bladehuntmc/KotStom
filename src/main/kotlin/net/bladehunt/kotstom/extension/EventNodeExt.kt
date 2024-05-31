package net.bladehunt.kotstom.extension

import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

fun <T : Event> EventNode<T>.addListeners(vararg listeners: EventListener<T>) =
    listeners.forEach(this::addListener)
