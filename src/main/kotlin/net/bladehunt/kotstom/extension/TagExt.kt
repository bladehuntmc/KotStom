package net.bladehunt.kotstom.extension

import kotlin.reflect.KProperty
import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagWritable

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
 * * @author oglassdev
 */
operator fun <T> Tag<T>.setValue(thisRef: TagWritable?, property: KProperty<*>, value: T) {
    thisRef?.setTag(this, value)
}
