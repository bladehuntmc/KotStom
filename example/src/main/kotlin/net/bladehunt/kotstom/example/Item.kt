package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.item.*
import net.bladehunt.kotstom.extension.asMini
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag

val exampleItem = item(Material.BOOK) {
    displayName = "Hey".asMini()
    amount = 5
    lore {
        +"<red>hello"
    }
    withMeta {
        tags {
            Tag.String("hello") - "123"
        }
    }
}