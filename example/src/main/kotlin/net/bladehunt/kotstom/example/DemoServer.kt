package net.bladehunt.kotstom.example

import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.Manager
import net.bladehunt.kotstom.dsl.listenOnly
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent

fun main() = runBlocking {
    val server = MinecraftServer.init()

    val instance = Manager.instance.createInstanceContainer()
    Manager.globalEvent.listenOnly<AsyncPlayerConfigurationEvent> { event ->
        event.spawningInstance = instance
    }

    playerLoginScheduler()
    Manager.command.register(ExampleCommand)

    server.start("127.0.0.1", 25565)
}