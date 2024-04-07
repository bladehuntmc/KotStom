package net.bladehunt.kstom.example

import kotlinx.coroutines.runBlocking
import net.bladehunt.kstom.Manager
import net.bladehunt.kstom.dsl.kommand.buildSyntax
import net.bladehunt.kstom.dsl.kommand.kommand
import net.bladehunt.kstom.dsl.listenOnly
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.arguments.ArgumentStringArray
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import world.cepi.kstom.command.register

fun main() = runBlocking {
    val server = MinecraftServer.init()

    val instance = Manager.instance.createInstanceContainer()
    Manager.globalEvent.listenOnly<AsyncPlayerConfigurationEvent> { event ->
        event.spawningInstance = instance
    }

    kommand {
        name = "hello"
        val exampleArg = ArgumentStringArray("my-strs")
        default {
            sender.sendMessage("wassup cuh")
        }
        buildSyntax(exampleArg) {
            executor {
                player.sendMessage("You're a player jit! You said ${exampleArg().joinToString(" ")}")
            }
        }
    }.register()

    server.start("127.0.0.1", 25565)
}