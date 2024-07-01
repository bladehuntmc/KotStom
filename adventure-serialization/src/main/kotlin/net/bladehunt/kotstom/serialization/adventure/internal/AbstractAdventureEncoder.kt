package net.bladehunt.kotstom.serialization.adventure.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.*

@ExperimentalSerializationApi
@InternalSerializationApi
internal abstract class AbstractAdventureEncoder : AdventureEncoder, CompositeEncoder {
    override val serializersModule: SerializersModule
        get() = adventureNbt.serializersModule

    override fun encodeNull() {
        throw SerializationException("'null' is not supported by default")
    }

    override fun encodeBoolean(value: Boolean) =
        encodeBinaryTag(if (value) ByteBinaryTag.ONE else ByteBinaryTag.ZERO)

    override fun encodeByte(value: Byte) = encodeBinaryTag(ByteBinaryTag.byteBinaryTag(value))

    override fun encodeChar(value: Char) = encodeString(value.toString())

    override fun encodeDouble(value: Double) =
        encodeBinaryTag(DoubleBinaryTag.doubleBinaryTag(value))

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) = encodeInt(index)

    override fun encodeFloat(value: Float) = encodeBinaryTag(FloatBinaryTag.floatBinaryTag(value))

    override fun encodeInline(descriptor: SerialDescriptor): Encoder = this

    override fun encodeInt(value: Int) = encodeBinaryTag(IntBinaryTag.intBinaryTag(value))

    override fun encodeLong(value: Long) = encodeBinaryTag(LongBinaryTag.longBinaryTag(value))

    override fun encodeShort(value: Short) = encodeBinaryTag(ShortBinaryTag.shortBinaryTag(value))

    override fun encodeString(value: String) =
        encodeBinaryTag(StringBinaryTag.stringBinaryTag(value))

    // Delegating implementation of CompositeEncoder
    final override fun encodeBooleanElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Boolean
    ) = encodeBoolean(value)

    final override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) =
        encodeByte(value)

    final override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) =
        encodeShort(value)

    final override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) =
        encodeInt(value)

    final override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) =
        encodeLong(value)

    final override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) =
        encodeFloat(value)

    final override fun encodeDoubleElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Double
    ) = encodeDouble(value)

    final override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) =
        encodeChar(value)

    final override fun encodeStringElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: String
    ) = encodeString(value)

    final override fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder =
        encodeInline(descriptor.getElementDescriptor(index))

    override fun <T : Any?> encodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T
    ) = encodeSerializableValue(serializer, value)

    override fun <T : Any> encodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T?
    ) = encodeNullableSerializableValue(serializer, value)
}
