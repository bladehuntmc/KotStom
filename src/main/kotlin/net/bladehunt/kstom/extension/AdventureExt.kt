package net.bladehunt.kstom.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

// Conversions
fun String.asMini(tagResolver: TagResolver = TagResolver.standard()): Component = MiniMessage.miniMessage().deserialize(this, tagResolver)
fun String.asLegacyAmpersand(): Component = LegacyComponentSerializer.legacyAmpersand().deserialize(this)

fun Component.toMiniMessage(): String = MiniMessage.miniMessage().serialize(this)
fun Component.toPlainText(): String = PlainTextComponentSerializer.plainText().serialize(this)
fun Component.toLegacyAmpersand(): String = LegacyComponentSerializer.legacyAmpersand().serialize(this)


// shamelessly copied it from the old one
fun Component.undecorate(decoration: TextDecoration): Component = decoration(decoration, TextDecoration.State.FALSE)
fun ComponentBuilder<*, *>.undecorate(decoration: TextDecoration): ComponentBuilder<*, *> = decoration(decoration, TextDecoration.State.FALSE)
fun Component.undecorate(vararg decorations: TextDecoration): Component = decorations(decorations.associateWith { TextDecoration.State.FALSE })
fun ComponentBuilder<*, *>.undecorate(vararg decorations: TextDecoration): ComponentBuilder<*, *> = decorations(decorations.associateWith { TextDecoration.State.FALSE })

fun Component.italic(): Component = decorate(TextDecoration.ITALIC)
fun ComponentBuilder<*, *>.italic(): ComponentBuilder<*, *> = decorate(TextDecoration.ITALIC)
fun Component.strikethrough(): Component = decorate(TextDecoration.STRIKETHROUGH)
fun ComponentBuilder<*, *>.strikethrough(): ComponentBuilder<*, *> = decorate(TextDecoration.STRIKETHROUGH)
fun Component.bold(): Component = decorate(TextDecoration.BOLD)
fun ComponentBuilder<*, *>.bold(): ComponentBuilder<*, *> = decorate(TextDecoration.BOLD)
fun Component.obfuscated(): Component = decorate(TextDecoration.OBFUSCATED)
fun ComponentBuilder<*, *>.obfuscated(): ComponentBuilder<*, *> = decorate(TextDecoration.OBFUSCATED)
fun Component.underlined(): Component = decorate(TextDecoration.UNDERLINED)
fun ComponentBuilder<*, *>.underlined(): ComponentBuilder<*, *> = decorate(TextDecoration.UNDERLINED)

fun Component.noItalic(): Component = undecorate(TextDecoration.ITALIC)
fun ComponentBuilder<*, *>.noItalic(): ComponentBuilder<*, *> = undecorate(TextDecoration.ITALIC)
fun Component.noStrikethrough(): Component = undecorate(TextDecoration.STRIKETHROUGH)
fun ComponentBuilder<*, *>.noStrikethrough(): ComponentBuilder<*, *> = undecorate(TextDecoration.STRIKETHROUGH)
fun Component.noBold(): Component = undecorate(TextDecoration.BOLD)
fun ComponentBuilder<*, *>.noBold(): ComponentBuilder<*, *> = undecorate(TextDecoration.BOLD)
fun Component.noObfuscated(): Component = undecorate(TextDecoration.OBFUSCATED)
fun ComponentBuilder<*, *>.noObfuscated(): ComponentBuilder<*, *> = undecorate(TextDecoration.OBFUSCATED)
fun Component.noUnderlined(): Component = undecorate(TextDecoration.UNDERLINED)
fun ComponentBuilder<*, *>.noUnderlined(): ComponentBuilder<*, *> = undecorate(TextDecoration.UNDERLINED)