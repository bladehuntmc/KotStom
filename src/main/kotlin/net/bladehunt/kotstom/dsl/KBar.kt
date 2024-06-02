package net.bladehunt.kotstom.dsl

import net.bladehunt.kotstom.util.KBar
import net.kyori.adventure.text.Component

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class KBarDsl

/**
 * `KBar` builder DSL
 *
 * @author oglassdev
 */
@KBarDsl
inline fun kbar(title: Component, block: @KBarDsl KBar.() -> Unit): KBar = KBar(title).apply(block)

/**
 * Creates and adds a `KBar.Line` to a `KBar`
 *
 * @see net.bladehunt.kotstom.util.KBar
 * @author oglassdev
 */
@KBarDsl
inline fun KBar.line(
    display: Component = Component.empty(),
    block: @KBarDsl KBar.Line.() -> Unit
): KBar.Line = Line(display).apply(block).apply(this::addLine)
