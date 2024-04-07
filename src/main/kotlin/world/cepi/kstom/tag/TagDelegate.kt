package world.cepi.kstom.tag

import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagWritable
import kotlin.reflect.KProperty

operator fun <T> Tag<T>.getValue(thisRef: TagReadable?, property: KProperty<*>): T? {
    return thisRef?.getTag(this)
}

operator fun <T> Tag<T>.setValue(thisRef: TagWritable?, property: KProperty<*>, value: T) {
    thisRef?.setTag(this, value)
}