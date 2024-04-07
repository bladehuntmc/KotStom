package net.bladehunt.kstom.example

import net.bladehunt.kstom.dsl.kommand.buildSyntax
import net.bladehunt.kstom.dsl.kommand.kommand
import net.minestom.server.command.builder.arguments.ArgumentStringArray

val ExampleCommand = kommand {
    name = "hello"

    val strings = ArgumentStringArray("strings")
    default {
        sender.sendMessage("wassup cuh")
    }
    buildSyntax(strings) {
        executor {
            player.sendMessage("You're a player jit! You said ${strings().joinToString(" ")}")
        }
    }
}