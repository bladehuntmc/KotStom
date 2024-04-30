package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.item.*
import net.bladehunt.kotstom.extension.asMini
import net.minestom.server.item.ItemComponent
import net.minestom.server.item.Material

val exampleItem = item(Material.WRITTEN_BOOK) {
    customName = "Hey".asMini()
    amount = 5
    lore {
        +"<red>hello"
    }
    ItemComponent.CUSTOM_MODEL_DATA(5)
}