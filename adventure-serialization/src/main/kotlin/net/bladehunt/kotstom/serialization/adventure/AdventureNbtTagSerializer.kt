package net.bladehunt.kotstom.serialization.adventure

import net.minestom.server.tag.TagHandler
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagSerializer
import net.minestom.server.tag.TagWritable
import org.jetbrains.annotations.ApiStatus
import kotlin.reflect.KClass

@ApiStatus.Experimental
class AdventureNbtTagSerializer<T : Any>(val adventureNbt: AdventureNbt, val clazz: KClass<T>) :
    TagSerializer<T> {
    override fun read(readable: TagReadable): T? {
        val handler = readable as? TagHandler ?: return null

        return adventureNbt.decodeFromCompound(clazz, handler.asCompound())
    }

    override fun write(writable: TagWritable, value: T) {
        val compound = adventureNbt.encodeToCompound(clazz, value)

        TagSerializer.COMPOUND.write(writable, compound)
    }
}