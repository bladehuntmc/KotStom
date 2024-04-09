package net.bladehunt.kotstom.dsl.item

import net.bladehunt.kotstom.extension.asMini
import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemMeta
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class ItemDsl

@JvmInline
value class ItemLore(
    private val list: MutableList<Component>
) : MutableList<Component> by list {
    operator fun Component.unaryPlus() {
        add(this)
    }
    operator fun String.unaryPlus() {
        add(this.asMini())
    }
}

@ItemDsl
inline fun ItemStack.Builder.lore(block: @ItemDsl ItemLore.() -> Unit) {
    lore(ItemLore(arrayListOf()).apply(block))
}
inline var ItemStack.Builder.amount: Int
    get() = 0
    set(value) { this.amount(value) }

inline var ItemStack.Builder.displayName: Component
    get() = Component.empty()
    set(value) { this.displayName(value) }

inline var ItemMeta.Builder.displayName: Component
    get() = Component.empty()
    set(value) { this.displayName(value) }

inline var ItemMeta.Builder.damage: Int
    get() = 0
    set(value) { this.damage(value) }

inline var ItemMeta.Builder.unbreakable: Boolean
    get() = false
    set(value) { this.unbreakable(value) }

@ItemDsl
inline fun item(
    material: Material = Material.STONE,
    block: @ItemDsl ItemStack.Builder.() -> Unit
): ItemStack = ItemStack.builder(material).apply(block).build()