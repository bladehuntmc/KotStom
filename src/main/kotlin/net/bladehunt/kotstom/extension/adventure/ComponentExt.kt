package net.bladehunt.kotstom.extension.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

/**
 * Converts a `Component` to a MiniMessage formatted `String`
 *
 * @return A `String` in MiniMessage format
 * @author oglassdev
 */
fun Component.toMiniMessage(): String = MiniMessage.miniMessage().serialize(this)

/**
 * Converts a `Component` to an unformatted `String`
 *
 * @return An unformatted `String`
 * @author oglassdev
 */
fun Component.toPlainText(): String = PlainTextComponentSerializer.plainText().serialize(this)

/**
 * Converts a `Component` to a legacy ampersand formatted `String`
 *
 * @return A `String` in legacy ampersand format
 * @author oglassdev
 */
fun Component.toLegacyAmpersand(): String =
    LegacyComponentSerializer.legacyAmpersand().serialize(this)

/**
 * Appends a `Component` to another `Component`
 *
 * @return The second component appended to the first component
 * @author oglassdev
 */
operator fun Component.plus(other: Component): Component = this.append(other)

/**
 * Appends a `String` to a `Component`
 *
 * @return The second component appended to the first component
 * @author oglassdev
 */
operator fun Component.plus(other: String): Component = this.append(Component.text(other))

// Copied from KStom. Original authors: DasLixou & LeoDog896
fun Component.undecorate(decoration: TextDecoration): Component =
    decoration(decoration, TextDecoration.State.FALSE)

fun ComponentBuilder<*, *>.undecorate(decoration: TextDecoration): ComponentBuilder<*, *> =
    decoration(decoration, TextDecoration.State.FALSE)

fun Component.undecorate(vararg decorations: TextDecoration): Component =
    decorations(decorations.associateWith { TextDecoration.State.FALSE })

fun ComponentBuilder<*, *>.undecorate(vararg decorations: TextDecoration): ComponentBuilder<*, *> =
    decorations(decorations.associateWith { TextDecoration.State.FALSE })

fun Component.italic(): Component = decorate(TextDecoration.ITALIC)

fun ComponentBuilder<*, *>.italic(): ComponentBuilder<*, *> = decorate(TextDecoration.ITALIC)

fun Component.strikethrough(): Component = decorate(TextDecoration.STRIKETHROUGH)

fun ComponentBuilder<*, *>.strikethrough(): ComponentBuilder<*, *> =
    decorate(TextDecoration.STRIKETHROUGH)

fun Component.bold(): Component = decorate(TextDecoration.BOLD)

fun ComponentBuilder<*, *>.bold(): ComponentBuilder<*, *> = decorate(TextDecoration.BOLD)

fun Component.obfuscated(): Component = decorate(TextDecoration.OBFUSCATED)

fun ComponentBuilder<*, *>.obfuscated(): ComponentBuilder<*, *> =
    decorate(TextDecoration.OBFUSCATED)

fun Component.underlined(): Component = decorate(TextDecoration.UNDERLINED)

fun ComponentBuilder<*, *>.underlined(): ComponentBuilder<*, *> =
    decorate(TextDecoration.UNDERLINED)

fun Component.noItalic(): Component = undecorate(TextDecoration.ITALIC)

fun ComponentBuilder<*, *>.noItalic(): ComponentBuilder<*, *> = undecorate(TextDecoration.ITALIC)

fun Component.noStrikethrough(): Component = undecorate(TextDecoration.STRIKETHROUGH)

fun ComponentBuilder<*, *>.noStrikethrough(): ComponentBuilder<*, *> =
    undecorate(TextDecoration.STRIKETHROUGH)

fun Component.noBold(): Component = undecorate(TextDecoration.BOLD)

fun ComponentBuilder<*, *>.noBold(): ComponentBuilder<*, *> = undecorate(TextDecoration.BOLD)

fun Component.noObfuscated(): Component = undecorate(TextDecoration.OBFUSCATED)

fun ComponentBuilder<*, *>.noObfuscated(): ComponentBuilder<*, *> =
    undecorate(TextDecoration.OBFUSCATED)

fun Component.noUnderlined(): Component = undecorate(TextDecoration.UNDERLINED)

fun ComponentBuilder<*, *>.noUnderlined(): ComponentBuilder<*, *> =
    undecorate(TextDecoration.UNDERLINED)
