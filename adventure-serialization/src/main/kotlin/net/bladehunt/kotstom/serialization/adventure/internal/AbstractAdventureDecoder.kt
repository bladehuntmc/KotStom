package net.bladehunt.kotstom.serialization.adventure.internal

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
@InternalSerializationApi
internal abstract class AbstractAdventureDecoder : AdventureDecoder, CompositeDecoder {
    override val serializersModule: SerializersModule
        get() = adventureNbt.serializersModule

    final override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean =
        decodeBoolean()

    final override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte =
        decodeByte()

    final override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char =
        decodeChar()

    final override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double =
        decodeDouble()

    final override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float =
        decodeFloat()

    final override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder =
        decodeInline(descriptor)

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int = decodeInt()

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long = decodeLong()

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? =
        if (descriptor.isNullable || decodeNotNullMark())
            decodeSerializableElement(descriptor, index, deserializer, previousValue)
        else null

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T = decodeSerializableValue(deserializer)

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short = decodeShort()

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String =
        decodeString()

    @ExperimentalSerializationApi override fun decodeNotNullMark(): Boolean = true

    @ExperimentalSerializationApi override fun decodeNull(): Nothing? = null
}
