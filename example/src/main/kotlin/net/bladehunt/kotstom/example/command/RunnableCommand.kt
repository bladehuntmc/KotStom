package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.runnable
import net.minestom.server.timer.TaskSchedule

val RunnableCommand = kommand {
    name = "runnable"
    default {
        sender.sendMessage("You aren't a player.")
    }
    buildSyntax {
        onlyPlayers()
        executor {
            runnable {
                var n = 0
                repeat = TaskSchedule.seconds(1)
                run {
                    if (n == 10) cancel()
                    player.sendMessage("This is the $n-th time")
                    n++
                }
            }.schedule(player.scheduler())
        }
    }
}