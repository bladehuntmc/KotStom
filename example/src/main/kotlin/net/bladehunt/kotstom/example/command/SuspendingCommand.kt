package net.bladehunt.kotstom.example.command

import kotlinx.coroutines.*
import net.bladehunt.kotstom.coroutines.MinestomDispatcher
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.runnable
import net.minestom.server.timer.TaskSchedule

val SuspendingCommand = kommand {
    name = "suspending"
    default {
        sender.sendMessage("You aren't a player.")
    }
    buildSyntax {
        onlyPlayers()
        executor {
            CoroutineScope(MinestomDispatcher).launch {
                repeat(11) { n ->
                    player.sendMessage("This is the $n-th time")
                    delay(1000)
                }
            }
        }
    }
}