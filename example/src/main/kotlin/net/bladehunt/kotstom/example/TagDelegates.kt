package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.extension.getValue
import net.bladehunt.kotstom.extension.lazy
import net.bladehunt.kotstom.extension.orElse
import net.bladehunt.kotstom.extension.setValue
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

var Player.myValue: String? by Tag.String("myValue")

var Player.myLazyValue: String by Tag.String("myValue").lazy { "default" }

val ItemStack.myDefaultValue: String by Tag.String("myValue").orElse { "default" }