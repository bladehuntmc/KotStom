package net.bladehunt.kstom.example

import kotlinx.coroutines.runBlocking
import net.bladehunt.kstom.Manager
import net.bladehunt.kstom.dsl.listenOnly
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import world.cepi.kstom.command.register

fun main() = runBlocking {
    val server = MinecraftServer.init()

    val instance = Manager.instance.createInstanceContainer()
    Manager.globalEvent.listenOnly<AsyncPlayerConfigurationEvent> { event ->
        event.spawningInstance = instance
    }

    playerLoginScheduler()
    ExampleCommand.register()

    server.start("127.0.0.1", 25565)
}