package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.defaultExecutor
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.scheduleTask
import net.minestom.server.timer.TaskSchedule

val RunnableCommand = kommand("runnable") {
    defaultExecutor { sender.sendMessage("You aren't a player.") }

    buildSyntax {
        onlyPlayers()
        executor {
            var n = 0

            player.scheduleTask(
                delay = TaskSchedule.seconds(1), repeat = TaskSchedule.seconds(1)
            ) { player ->
                player.sendMessage("$n times")

                n++
            }
        }
    }
}
