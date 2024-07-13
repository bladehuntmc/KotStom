package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.findPolymorphicSerializer
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.bladehunt.kotstom.serialization.adventure.internal.Consumer
import net.bladehunt.kotstom.serialization.adventure.internal.TaggedAdventureEncoder
import net.kyori.adventure.nbt.BinaryTag
import net.kyori.adventure.nbt.CompoundBinaryTag

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class AdventureCompoundEncoder(
    override val adventureNbt: AdventureNbt,
    private val consumer: Consumer<CompoundBinaryTag>? = null
) : TaggedAdventureEncoder() {
    private val tags: MutableMap<String, BinaryTag> = linkedMapOf()

    override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean =
        adventureNbt.shouldEncodeDefaults

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        if (serializer !is AbstractPolymorphicSerializer<*>)
            return super.encodeSerializableValue(serializer, value)

        @Suppress("UNCHECKED_CAST") val casted = serializer as AbstractPolymorphicSerializer<Any>

        val encoder =
            if (currentTagOrNull == null)
                AdventureCompoundEncoder(adventureNbt) {
                    it.forEach { (key, tag) -> tags[key] = tag }
                }
            else AdventureCompoundEncoder(adventureNbt) { encodeTaggedBinaryTag(currentTag, it) }
        val actual = casted.findPolymorphicSerializer(encoder, value as Any)

        encoder.encodeTaggedString(adventureNbt.discriminator, actual.descriptor.serialName)
        actual.serialize(encoder, value)
    }

    override fun encodeTaggedBinaryTag(tag: String, binaryTag: BinaryTag) {
        tags[tag] = binaryTag
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        if (currentTagOrNull == null) return super.beginStructure(descriptor)

        return when (descriptor.kind) {
            is StructureKind.LIST -> {
                AdventureListEncoder(adventureNbt) { encodeTaggedBinaryTag(popTag(), it) }
            }
            else -> {
                AdventureCompoundEncoder(adventureNbt) { encodeTaggedBinaryTag(popTag(), it) }
            }
        }
    }

    override fun endEncode(descriptor: SerialDescriptor) {
        consumer?.invoke(toBinaryTag())
    }

    override fun toBinaryTag(): CompoundBinaryTag {
        return CompoundBinaryTag.from(tags)
    }
}
