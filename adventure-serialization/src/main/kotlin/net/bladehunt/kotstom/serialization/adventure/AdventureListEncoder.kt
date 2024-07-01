package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.findPolymorphicSerializer
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.bladehunt.kotstom.serialization.adventure.internal.AbstractAdventureEncoder
import net.bladehunt.kotstom.serialization.adventure.internal.Consumer
import net.kyori.adventure.nbt.BinaryTag
import net.kyori.adventure.nbt.ListBinaryTag
import net.kyori.adventure.nbt.StringBinaryTag

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class AdventureListEncoder(
    override val adventureNbt: AdventureNbt,
    private val consumer: Consumer<ListBinaryTag>? = null
) : AbstractAdventureEncoder() {
    private val tags: MutableList<BinaryTag> = arrayListOf()

    override fun encodeBinaryTag(binaryTag: BinaryTag) {
        tags.add(binaryTag)
    }

    override fun toBinaryTag(): ListBinaryTag = ListBinaryTag.from(tags)

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        if (serializer !is AbstractPolymorphicSerializer<*>)
            return super.encodeSerializableValue(serializer, value)

        @Suppress("UNCHECKED_CAST") val casted = serializer as AbstractPolymorphicSerializer<Any>

        val encoder = AdventureCompoundEncoder(adventureNbt) { encodeBinaryTag(it) }
        val actual = casted.findPolymorphicSerializer(encoder, value as Any)

        encoder.encodeTaggedBinaryTag(
            adventureNbt.discriminator,
            StringBinaryTag.stringBinaryTag(actual.descriptor.serialName))

        actual.serialize(encoder, value)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return when (descriptor.kind) {
            is StructureKind.LIST -> {
                AdventureListEncoder(adventureNbt) { encodeBinaryTag(it) }
            }
            else -> {
                AdventureCompoundEncoder(adventureNbt) { encodeBinaryTag(it) }
            }
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        consumer?.invoke(toBinaryTag())
    }
}
