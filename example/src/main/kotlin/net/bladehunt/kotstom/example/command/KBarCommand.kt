package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.kbar
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.line
import net.bladehunt.kotstom.extension.adventure.asMini
import net.bladehunt.kotstom.extension.adventure.text
import net.kyori.adventure.text.format.NamedTextColor

val KBarCommand = kommand {
    name = "kbar"
    defaultExecutor { sender.sendMessage("You aren't a player.") }
    buildSyntax {
        onlyPlayers()
        executor {
            val kbar =
                kbar("<white>Hello".asMini()) {
                    line { display = text("First Line", NamedTextColor.WHITE) }

                    line { display = text("Second Line", NamedTextColor.WHITE) }
                }

            kbar.addViewer(player)
        }
    }
}
