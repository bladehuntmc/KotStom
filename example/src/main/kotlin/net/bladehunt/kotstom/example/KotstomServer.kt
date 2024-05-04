package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.CommandManager
import net.bladehunt.kotstom.GlobalEventHandler
import net.bladehunt.kotstom.InstanceManager
import net.bladehunt.kotstom.dsl.builder
import net.bladehunt.kotstom.dsl.listen
import net.bladehunt.kotstom.example.command.ItemCommand
import net.bladehunt.kotstom.example.command.ParticleCommand
import net.bladehunt.kotstom.example.command.RunnableCommand
import net.bladehunt.kotstom.example.command.SuspendingCommand
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
    }
    GlobalEventHandler.listen<PlayerSpawnEvent> { event ->
        event.player.teleport(Pos(0.5, 16.0, 0.5))
    }
    GlobalEventHandler.builder<PlayerSpawnEvent> {
        expireCount = 10
        filter { it.isFirstSpawn }
        handler { event ->
            event.player.sendMessage("You were within the first 10")
        }
    }

    CommandManager.register(ItemCommand, ParticleCommand, RunnableCommand, SuspendingCommand)

    minecraftServer.start("127.0.0.1", 25565)
}