package net.bladehunt.kotstom

import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagWritable
import kotlin.reflect.KProperty

class DefaultTagDelegate<T : TagReadable, V>(
    val tag: Tag<V>,
    val default: (T) -> V
) {
    operator fun getValue(thisRef: T, property: KProperty<*>): V =
        if (thisRef.hasTag(tag)) thisRef.getTag(tag)
        else default(thisRef)

    operator fun setValue(thisRef: TagWritable, property: KProperty<*>, value: V) {
        thisRef.setTag(tag, value)
    }
}

class LazyTagDelegate<T : TagWritable, V>(
    val tag: Tag<V>,
    val default: (T) -> V
) {
    operator fun getValue(thisRef: T, property: KProperty<*>): V =
        if (thisRef.hasTag(tag)) thisRef.getTag(tag)
        else default(thisRef).also { thisRef.setTag(tag, it) }

    operator fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        thisRef.setTag(tag, value)
    }
}