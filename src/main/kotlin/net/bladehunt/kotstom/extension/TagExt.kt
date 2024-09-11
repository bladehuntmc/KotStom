package net.bladehunt.kotstom.extension

import net.bladehunt.kotstom.DefaultTagDelegate
import net.bladehunt.kotstom.LazyTagDelegate
import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagWritable
import kotlin.reflect.KProperty

/**
 * Delegation utility for getting the value of a tag
 *
 * @author oglassdev
 */
operator fun <T> Tag<T>.getValue(thisRef: TagReadable?, property: KProperty<*>): T? {
    return thisRef?.getTag(this)
}

/**
 * Delegation utility for setting the value of a tag
 *
 * @author oglassdev
 */
operator fun <T> Tag<T>.setValue(thisRef: TagWritable?, property: KProperty<*>, value: T) {
    thisRef?.setTag(this, value)
}

/**
 * Tag delegate that lazily sets a default value on a TagWritable if it doesn't exist.
 * Otherwise, returns the tag value.
 *
 * @author oglassdev
 */
fun <W : TagWritable, T> Tag<T>.lazy(block: (W) -> T): LazyTagDelegate<W, T> =
    LazyTagDelegate(this, block)

/**
 * Tag delegate that returns a default if the TagReadable doesn't contain the tag.
 *
 * @author oglassdev
 */
fun <W : TagReadable, T> Tag<T>.orElse(default: (W) -> T): DefaultTagDelegate<W, T> =
    DefaultTagDelegate(this, default)