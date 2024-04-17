package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.minestom.server.command.builder.arguments.ArgumentStringArray

val ExampleCommand = kommand {
    name = "hello"

    val strings = ArgumentStringArray("strings")
    default {
        sender.sendMessage("wassup cuh")
    }
    subkommand {
        name = "mysubcommand"
        default {
            sender.sendMessage("You did the subcommand!")
        }
    }
    buildSyntax(strings) {
        executor {
            player.sendMessage("You're a player jit! You said ${strings().joinToString(" ")}")
        }
    }
}