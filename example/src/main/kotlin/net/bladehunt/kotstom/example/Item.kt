package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.item.*
import net.bladehunt.kotstom.extension.asMini
import net.kyori.adventure.text.Component
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.WrittenBookMeta
import net.minestom.server.tag.Tag

val exampleItem = item(Material.WRITTEN_BOOK) {
    displayName = "Hey".asMini()
    amount = 5
    lore {
        +"<red>hello"
    }
    meta<WrittenBookMeta.Builder, WrittenBookMeta> {
        author(Component.text("Hello"))
        tags {
            // `-` means set Tag to Value
            Tag.String("hello") - "123"
        }
    }
    // Alternatively, if you don't need a special meta type, just use `withMeta`
    withMeta {
        tags {
            // `-` means set Tag to Value
            Tag.String("hello") - "123"
        }
    }
}