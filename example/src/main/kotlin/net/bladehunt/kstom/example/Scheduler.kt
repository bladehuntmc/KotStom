package net.bladehunt.kstom.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.bladehunt.kstom.Manager
import net.bladehunt.kstom.dsl.listenOnly
import net.bladehunt.kstom.extension.await
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.timer.TaskSchedule

fun playerLoginScheduler(coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default), node: EventNode<in AsyncPlayerConfigurationEvent> = Manager.globalEvent) {
    node.listenOnly { event ->
        val player = event.player
        coroutineScope.launch {
            player.await(TaskSchedule.nextTick())
            player.sendMessage("Waited for next tick")
            player.await(TaskSchedule.seconds(5))
            player.sendMessage("Waited 5 seconds")
        }
    }
}