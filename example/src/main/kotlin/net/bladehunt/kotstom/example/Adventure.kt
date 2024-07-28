package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.extension.adventure.plus
import net.bladehunt.kotstom.extension.adventure.text
import net.kyori.adventure.text.format.NamedTextColor.GREEN
import net.kyori.adventure.text.format.NamedTextColor.RED

val stringExtensionExample = text("Hello ", GREEN) + text("world", RED)
