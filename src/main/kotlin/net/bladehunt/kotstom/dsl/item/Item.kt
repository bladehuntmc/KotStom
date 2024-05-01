package net.bladehunt.kotstom.dsl.item

import net.bladehunt.kotstom.extension.asMini
import net.kyori.adventure.text.Component
import net.minestom.server.component.DataComponent
import net.minestom.server.item.ItemComponent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.item.component.Unbreakable

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.PROPERTY)
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
    set(ItemComponent.LORE, ItemLore(arrayListOf()).apply(block))
}

@ItemDsl
inline var ItemStack.Builder.amount: Int
    get() = 0
    set(value) { this.amount(value) }

@ItemDsl
inline var ItemStack.Builder.itemName: Component
    get() = Component.empty()
    set(value) {
        this.set(ItemComponent.ITEM_NAME, value)
    }

@ItemDsl
inline var ItemStack.Builder.customName: Component
    get() = Component.empty()
    set(value) {
        this.set(ItemComponent.CUSTOM_NAME, value)
    }

@ItemDsl
inline var ItemStack.Builder.damage: Int
    get() = 0
    set(value) {
        this.set(ItemComponent.DAMAGE, value)
    }

@ItemDsl
inline var ItemStack.Builder.unbreakable: Unbreakable
    get() = Unbreakable.DEFAULT
    set(value) {
        this.set(ItemComponent.UNBREAKABLE, value)
    }

context(builder@ItemStack.Builder)
@ItemDsl
inline operator fun <T> DataComponent<T>.invoke(value: T) {
    this@builder.set(this, value)
}

@ItemDsl
inline fun item(
    material: Material = Material.STONE,
    block: @ItemDsl ItemStack.Builder.() -> Unit
): ItemStack = ItemStack.builder(material).apply(block).build()