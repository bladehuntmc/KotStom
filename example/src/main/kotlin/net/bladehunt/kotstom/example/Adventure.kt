package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.extension.adventure.color
import net.bladehunt.kotstom.extension.adventure.plus
import net.kyori.adventure.text.format.NamedTextColor

val stringExtensionExample =
    "Hello ".color(NamedTextColor.GREEN) + "world".color(NamedTextColor.RED)
