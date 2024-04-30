package net.bladehunt.kotstom.example

import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.CommandManager
import net.bladehunt.kotstom.GlobalEventHandler
import net.bladehunt.kotstom.InstanceManager
import net.bladehunt.kotstom.dsl.listenOnly
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.event.player.PlayerSpawnEvent

fun main() = runBlocking {
    val server = MinecraftServer.init()

    val instance = InstanceManager.createInstanceContainer()
    GlobalEventHandler.listenOnly<AsyncPlayerConfigurationEvent> { event ->
        event.spawningInstance = instance
    }
    GlobalEventHandler.listenOnly<PlayerSpawnEvent> { event ->
        event.player.openInventory(eventNodeInventory)
    }

    playerLoginScheduler()
    CommandManager.register(ExampleCommand)

    server.start("127.0.0.1", 25565)
}