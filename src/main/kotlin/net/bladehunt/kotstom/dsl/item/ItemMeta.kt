package net.bladehunt.kotstom.dsl.item

import net.minestom.server.item.ItemMeta
import net.minestom.server.item.ItemMetaView
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

@ItemDsl
inline fun <reified V : ItemMetaView.Builder, reified T : ItemMetaView<V>> ItemStack.Builder.meta(
    noinline block: V.() -> Unit
) {
    this.meta(T::class.java, block)
}

@ItemDsl
inline fun ItemStack.Builder.withMeta(crossinline block: @ItemDsl ItemMeta.Builder.() -> Unit) =
    this.meta {
        it.block()
    }

@JvmInline
value class ItemNbt(private val meta: ItemMeta.Builder) {
    operator fun <T> Tag<T>.minus(value: T) {
        meta.setTag(this, value)
    }
}

@ItemDsl
inline fun ItemMeta.Builder.tags(crossinline block: @ItemDsl ItemNbt.() -> Unit) {
    ItemNbt(this).block()
}