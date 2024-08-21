package net.bladehunt.kotstom.extras.dsl

import net.bladehunt.kotstom.extras.util.ReactiveSidebar
import net.kyori.adventure.text.Component

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class SidebarDsl

/**
 * `ReactiveSidebar` builder DSL
 *
 * @author oglassdev
 */
@SidebarDsl
inline fun reactiveSidebar(
    title: Component,
    block: @SidebarDsl ReactiveSidebar.() -> Unit
): ReactiveSidebar = ReactiveSidebar(title).apply(block)
