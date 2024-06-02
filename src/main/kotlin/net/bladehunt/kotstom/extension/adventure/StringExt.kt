package net.bladehunt.kotstom.extension.adventure

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

/**
 * Converts a `String` to a `Component` using a MiniMessage `TagResolver`. If no `TagResolver` is
 * specified, then it uses the standard `TagResolver`.
 *
 * @param tagResolver The TagResolver to use
 * @return A formatted `Component`
 * @author oglassdev
 */
fun String.asMini(tagResolver: TagResolver = TagResolver.standard()): Component =
    MiniMessage.miniMessage().deserialize(this, tagResolver)

/**
 * Converts a `String` to a `Component` using the `LegacyComponentSerializer`. Prefer using
 * MiniMessage when possible.
 *
 * @return A formatted `Component`
 * @author oglassdev
 */
fun String.asLegacyAmpersand(): Component =
    LegacyComponentSerializer.legacyAmpersand().deserialize(this)

/**
 * Converts a `String` to a `Component` with a color
 *
 * @param color The color to apply
 * @return A `Component`
 * @author oglassdev
 */
fun String.color(color: TextColor?): Component = Component.text(this, color)

/**
 * Converts a `String` to a `Component`
 *
 * @return A `Component`
 * @author oglassdev
 */
fun String.asComponent(): Component = Component.text(this)

/**
 * Converts a `String` to a `Component` with a decoration
 *
 * @param color The color to apply
 * @return A `Component`
 * @author oglassdev
 */
fun String.decorate(decoration: TextDecoration, value: Boolean = true): Component =
    Component.text(this).decoration(decoration, value)
