package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.item.*
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.extension.asMini
import net.bladehunt.kotstom.extension.undecorate
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.Material
import net.minestom.server.item.component.Unbreakable

val ItemCommand = kommand {
    name = "item"
    default { sender.sendMessage("You aren't a player.") }
    buildSyntax {
        onlyPlayers()
        executor {
            player.inventory.addItemStack(
                item(Material.SNOW) {
                    itemName = "<red>Red Snow".asMini()
                    lore {
                        +"<gold>This is some good snow".asMini()
                        +Component.text("Maybe...").undecorate(TextDecoration.ITALIC)
                    }
                    unbreakable = Unbreakable(true)
                }
            )
        }
    }
}
