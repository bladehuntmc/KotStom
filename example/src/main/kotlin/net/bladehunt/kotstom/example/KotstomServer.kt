package net.bladehunt.kotstom.example

import kotlinx.coroutines.delay
import net.bladehunt.kotstom.CommandManager
import net.bladehunt.kotstom.GlobalEventHandler
import net.bladehunt.kotstom.InstanceManager
import net.bladehunt.kotstom.dsl.builder
import net.bladehunt.kotstom.dsl.listen
import net.bladehunt.kotstom.example.command.*
import net.bladehunt.kotstom.extension.register
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.instance.LightingChunk
import net.minestom.server.instance.block.Block

fun main() {
    val minecraftServer = MinecraftServer.init()

    val instance = InstanceManager.createInstanceContainer()
    instance.setGenerator { it.modifier().fillHeight(0, 16, Block.BONE_BLOCK) }
    instance.setChunkSupplier(::LightingChunk)

    GlobalEventHandler.listen<AsyncPlayerConfigurationEvent> { event ->
        event.spawningInstance = instance
        event.player.respawnPoint = Pos(0.5, 16.0, 0.5)
    }
    GlobalEventHandler.builder<PlayerSpawnEvent> {
        expireCount = 10
        filter { it.isFirstSpawn }
        asyncHandler { event ->
            event.player.sendMessage("You were within the first 10")
            delay(5000)
            event.player.sendMessage("Waited 5 seconds")
        }
    }

    CommandManager.register(
        ItemCommand,
        ParticleCommand,
        RunnableCommand,
        SuspendingCommand,
        KBarCommand
    )

    minecraftServer.start("127.0.0.1", 25565)
}
