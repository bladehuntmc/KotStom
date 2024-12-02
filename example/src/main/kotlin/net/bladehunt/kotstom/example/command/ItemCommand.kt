package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.item.item
import net.bladehunt.kotstom.dsl.item.itemName
import net.bladehunt.kotstom.dsl.item.lore
import net.bladehunt.kotstom.dsl.item.unbreakable
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.defaultExecutor
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.extension.adventure.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.Material
import net.minestom.server.item.component.Unbreakable

val ItemCommand = kommand("item") {
    defaultExecutor { sender.sendMessage("You aren't a player.") }

    buildSyntax {
        onlyPlayers()
        executor {
            player.inventory.addItemStack(
                item(Material.SNOW) {
                    itemName = text("Red Snow", NamedTextColor.RED)
                    lore {
                        +text("This is some good snow", NamedTextColor.GOLD)
                        +text("Maybe...", TextDecoration.ITALIC to false)
                    }
                    unbreakable = Unbreakable(true)
                })
        }
    }
}
