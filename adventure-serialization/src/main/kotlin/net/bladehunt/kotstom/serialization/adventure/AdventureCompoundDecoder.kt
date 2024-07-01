package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.bladehunt.kotstom.serialization.adventure.internal.TaggedAdventureDecoder
import net.kyori.adventure.nbt.BinaryTag
import net.kyori.adventure.nbt.CompoundBinaryTag

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class AdventureCompoundDecoder(
    override val adventureNbt: AdventureNbt,
    private val compound: CompoundBinaryTag
) : TaggedAdventureDecoder() {
    private var index = -1

    override fun decodeTaggedBinaryTag(tag: String): BinaryTag = compound[tag]!!

    override fun decodeTaggedBoolean(tag: String): Boolean = compound.getBoolean(tag)

    override fun decodeTaggedByte(tag: String): Byte = compound.getByte(tag)

    override fun decodeTaggedShort(tag: String): Short = compound.getShort(tag)

    override fun decodeTaggedInt(tag: String): Int = compound.getInt(tag)

    override fun decodeTaggedLong(tag: String): Long = compound.getLong(tag)

    override fun decodeTaggedFloat(tag: String): Float = compound.getFloat(tag)

    override fun decodeTaggedDouble(tag: String): Double = compound.getDouble(tag)

    override fun decodeTaggedChar(tag: String): Char = compound.getString(tag)[0]

    override fun decodeTaggedString(tag: String): String = compound.getString(tag)

    override fun decodeTaggedEnum(tag: String, enumDescriptor: SerialDescriptor): Int =
        decodeTaggedInt(tag)

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        index++
        return if (index >= descriptor.elementsCount) CompositeDecoder.DECODE_DONE else index
    }

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
        if (deserializer !is AbstractPolymorphicSerializer<*>)
            return super.decodeSerializableValue(deserializer)

        val compound = if (currentTagOrNull != null) compound.getCompound(currentTag) else compound

        val decoder = AdventureCompoundDecoder(adventureNbt, compound)
        val type = decoder.decodeTaggedString(adventureNbt.discriminator)
        val actualSerializer =
            deserializer.findPolymorphicSerializerOrNull(decoder, type)
                ?: throw SerializationException("$type was not found!")

        @Suppress("UNCHECKED_CAST")
        return (actualSerializer as DeserializationStrategy<T>).deserialize(decoder)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        if (currentTagOrNull == null) return this

        return when (descriptor.kind) {
            is StructureKind.LIST -> {
                AdventureListDecoder(adventureNbt, compound.getList(currentTag))
            }
            else -> {
                AdventureCompoundDecoder(adventureNbt, compound.getCompound(currentTag))
            }
        }
    }
}
