package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import net.bladehunt.kotstom.serialization.adventure.internal.AbstractAdventureDecoder
import net.kyori.adventure.nbt.*

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class AdventureListDecoder(
    override val adventureNbt: AdventureNbt,
    private val list: ListBinaryTag
) : AbstractAdventureDecoder() {
    private var index = -1

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        index++
        return if (index == list.size()) CompositeDecoder.DECODE_DONE else index
    }

    override fun decodeBinaryTag(): BinaryTag = list[index]

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
        if (deserializer !is AbstractPolymorphicSerializer<*>)
            return super.decodeSerializableValue(deserializer)

        val compound = list.getCompound(index)
        val decoder = AdventureCompoundDecoder(adventureNbt, compound)
        val type =
            (decoder.decodeTaggedBinaryTag(adventureNbt.discriminator) as StringBinaryTag).value()
        val actualSerializer =
            deserializer.findPolymorphicSerializerOrNull(decoder, type)
                ?: throw SerializationException("$type was not found!")

        @Suppress("UNCHECKED_CAST")
        return (actualSerializer as DeserializationStrategy<T>).deserialize(decoder)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return when (descriptor.kind) {
            is StructureKind.LIST -> {
                AdventureListDecoder(adventureNbt, (decodeBinaryTag() as ListBinaryTag))
            }
            else -> {
                AdventureCompoundDecoder(adventureNbt, decodeBinaryTag() as CompoundBinaryTag)
            }
        }
    }

    override fun decodeBoolean(): Boolean =
        (decodeBinaryTag() as ByteBinaryTag).value() == 0x01.toByte()

    override fun decodeByte(): Byte = (decodeBinaryTag() as ByteBinaryTag).value()

    override fun decodeChar(): Char = decodeString()[0]

    override fun decodeDouble(): Double = (decodeBinaryTag() as DoubleBinaryTag).value()

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int = decodeInt()

    override fun decodeFloat(): Float = (decodeBinaryTag() as FloatBinaryTag).value()

    override fun decodeInline(descriptor: SerialDescriptor): Decoder = this

    override fun decodeInt(): Int = (decodeBinaryTag() as IntBinaryTag).value()

    override fun decodeLong(): Long = (decodeBinaryTag() as LongBinaryTag).value()

    override fun decodeShort(): Short = (decodeBinaryTag() as ShortBinaryTag).value()

    override fun decodeString(): String = (decodeBinaryTag() as StringBinaryTag).value()

    override fun endStructure(descriptor: SerialDescriptor) {}
}
