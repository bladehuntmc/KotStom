package net.bladehunt.kotstom.serialization.adventure.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.NamedValueEncoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.*

@InternalSerializationApi
internal abstract class TaggedAdventureEncoder : AdventureEncoder, NamedValueEncoder() {
    override val serializersModule: SerializersModule
        get() = adventureNbt.serializersModule

    abstract fun encodeTaggedBinaryTag(tag: String, binaryTag: BinaryTag)

    final override fun encodeTaggedInt(tag: String, value: Int): Unit =
        encodeTaggedBinaryTag(tag, IntBinaryTag.intBinaryTag(value))

    final override fun encodeTaggedByte(tag: String, value: Byte): Unit =
        encodeTaggedBinaryTag(tag, ByteBinaryTag.byteBinaryTag(value))

    final override fun encodeTaggedShort(tag: String, value: Short): Unit =
        encodeTaggedBinaryTag(tag, ShortBinaryTag.shortBinaryTag(value))

    final override fun encodeTaggedLong(tag: String, value: Long): Unit =
        encodeTaggedBinaryTag(tag, LongBinaryTag.longBinaryTag(value))

    final override fun encodeTaggedFloat(tag: String, value: Float): Unit =
        encodeTaggedBinaryTag(tag, FloatBinaryTag.floatBinaryTag(value))

    final override fun encodeTaggedDouble(tag: String, value: Double): Unit =
        encodeTaggedBinaryTag(tag, DoubleBinaryTag.doubleBinaryTag(value))

    final override fun encodeTaggedBoolean(tag: String, value: Boolean): Unit =
        encodeTaggedBinaryTag(tag, if (value) ByteBinaryTag.ONE else ByteBinaryTag.ZERO)

    final override fun encodeTaggedChar(tag: String, value: Char): Unit =
        encodeString(value.toString())

    final override fun encodeTaggedString(tag: String, value: String): Unit =
        encodeTaggedBinaryTag(tag, StringBinaryTag.stringBinaryTag(value))

    final override fun encodeTaggedEnum(
        tag: String,
        enumDescriptor: SerialDescriptor,
        ordinal: Int
    ): Unit = encodeTaggedInt(tag, ordinal)

    final override fun encodeBinaryTag(binaryTag: BinaryTag) =
        encodeTaggedBinaryTag(popTag(), binaryTag)
}
