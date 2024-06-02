package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.kbar
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.line
import net.bladehunt.kotstom.dsl.runnable
import net.bladehunt.kotstom.extension.adventure.asMini
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.timer.TaskSchedule

val KBarCommand = kommand {
    name = "kbar"
    defaultExecutor { sender.sendMessage("You aren't a player.") }
    buildSyntax {
        onlyPlayers()
        executor {
            val kbar =
                kbar("<white>Hello".asMini()) {
                    line {
                        display = Component.text("First Line").color(NamedTextColor.WHITE)

                        val task = runnable {
                            delay = TaskSchedule.seconds(5)
                            run { display = Component.text("Changed").color(NamedTextColor.WHITE) }
                        }

                        task.schedule(player.scheduler())
                    }
                    line { display = Component.text("Second Line").color(NamedTextColor.WHITE) }
                }

            kbar.addViewer(player)

            runnable {
                    delay = TaskSchedule.seconds(10)
                    run { kbar.removeViewer(player) }
                }
                .schedule(player.scheduler())
        }
    }
}
