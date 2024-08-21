package net.bladehunt.kotstom.example.command

import kotlinx.coroutines.flow.MutableStateFlow
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.scheduleTask
import net.bladehunt.kotstom.extension.adventure.asMini
import net.bladehunt.kotstom.extension.adventure.text
import net.bladehunt.kotstom.extras.dsl.reactiveSidebar
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.timer.TaskSchedule

val ReactiveSidebarCommand = kommand {
    name = "sidebar"
    defaultExecutor { sender.sendMessage("You aren't a player.") }
    buildSyntax {
        onlyPlayers()
        executor {
            val state = MutableStateFlow("asd")

            val kbar =
                reactiveSidebar("<white>Hello".asMini()) {
                    +Line { text(state() + " first", NamedTextColor.WHITE) }

                    +Line { text("second " + state(), NamedTextColor.WHITE) }
                }

            kbar.addViewer(player)

            player.scheduleTask(delay = TaskSchedule.seconds(3), repeat = TaskSchedule.stop()) {
                state.value = "123"
            }
        }
    }
}
