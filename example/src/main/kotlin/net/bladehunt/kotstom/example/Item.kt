package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.item.*
import net.bladehunt.kotstom.extension.asMini
import net.minestom.server.item.ItemComponent
import net.minestom.server.item.Material

val exampleItem = item(Material.WRITTEN_BOOK) {
    // customName and itemName are now separate
    customName = "Hey".asMini()
    amount = 5
    lore {
        +"<red>hello"
    }
    // Components can be set like so
    ItemComponent.CUSTOM_MODEL_DATA(5)

    // ... or with the normal one - you decide
    set(ItemComponent.CUSTOM_MODEL_DATA, 5)
}