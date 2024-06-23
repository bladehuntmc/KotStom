package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.findPolymorphicSerializer
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.internal.NamedValueEncoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.*

@OptIn(InternalSerializationApi::class)
@ExperimentalSerializationApi
internal class AdventureEncoder(
    private val adventureSerializer: AdventureSerializer,
    private val consumeValue: ((CompoundBinaryTag) -> Unit)? = null
) : NamedValueEncoder() {
    private val tagMap: MutableMap<String, BinaryTag> = hashMapOf()

    override val serializersModule: SerializersModule
        get() = adventureSerializer.serializersModule

    override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean =
        adventureSerializer.encodeDefaults

    override fun encodeTaggedInt(tag: String, value: Int) {
        tagMap[tag] = IntBinaryTag.intBinaryTag(value)
    }

    override fun encodeTaggedByte(tag: String, value: Byte) {
        tagMap[tag] = ByteBinaryTag.byteBinaryTag(value)
    }

    override fun encodeTaggedShort(tag: String, value: Short) {
        tagMap[tag] = ShortBinaryTag.shortBinaryTag(value)
    }

    override fun encodeTaggedLong(tag: String, value: Long) {
        tagMap[tag] = LongBinaryTag.longBinaryTag(value)
    }

    override fun encodeTaggedFloat(tag: String, value: Float) {
        tagMap[tag] = FloatBinaryTag.floatBinaryTag(value)
    }

    override fun encodeTaggedDouble(tag: String, value: Double) {
        tagMap[tag] = DoubleBinaryTag.doubleBinaryTag(value)
    }

    override fun encodeTaggedBoolean(tag: String, value: Boolean) {
        tagMap[tag] = if (value) ByteBinaryTag.ONE else ByteBinaryTag.ZERO
    }

    override fun encodeTaggedChar(tag: String, value: Char) =
        encodeTaggedString(tag, value.toString())

    override fun encodeTaggedString(tag: String, value: String) {
        tagMap[tag] = StringBinaryTag.stringBinaryTag(value)
    }

    override fun encodeTaggedEnum(tag: String, enumDescriptor: SerialDescriptor, ordinal: Int) =
        encodeTaggedInt(tag, ordinal)

    override fun encodeTaggedInline(tag: String, inlineDescriptor: SerialDescriptor): Encoder =
        this.apply { pushTag(tag) }

    private var isPolymorphic: Boolean = false

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        when {
            serializer !is AbstractPolymorphicSerializer<*> -> serializer.serialize(this, value)
            else -> {
                @Suppress("UNCHECKED_CAST")
                val casted = serializer as AbstractPolymorphicSerializer<Any>
                val actualSerializer = casted.findPolymorphicSerializer(this, value as Any)
                isPolymorphic = true

                actualSerializer.serialize(
                    beginStructure(actualSerializer.descriptor) as Encoder, value)
            }
        }
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        if (isPolymorphic) {
            isPolymorphic = false

            if (currentTagOrNull == null) {
                encodeTaggedString("type", descriptor.serialName)
                return when (descriptor.kind) {
                    is StructureKind.LIST ->
                        AdventureListEncoder(adventureSerializer) { list -> tagMap["value"] = list }
                    else ->
                        AdventureEncoder(adventureSerializer) { value -> tagMap["value"] = value }
                }
            }

            return when (descriptor.kind) {
                is StructureKind.LIST ->
                    AdventureListEncoder(adventureSerializer) { value ->
                        tagMap[popTag()] =
                            CompoundBinaryTag.builder()
                                .putString("type", descriptor.serialName)
                                .put("value", value)
                                .build()
                    }
                else ->
                    AdventureEncoder(adventureSerializer) { value ->
                        tagMap[popTag()] =
                            CompoundBinaryTag.builder()
                                .putString("type", descriptor.serialName)
                                .put("value", value)
                                .build()
                    }
            }
        }
        if (currentTagOrNull == null) return this
        return when (descriptor.kind) {
            is StructureKind.LIST ->
                AdventureListEncoder(adventureSerializer) { list -> tagMap[popTag()] = list }
            else -> AdventureEncoder(adventureSerializer) { value -> tagMap[popTag()] = value }
        }
    }

    override fun endEncode(descriptor: SerialDescriptor) {
        consumeValue?.invoke(asCompound())
    }

    fun asCompound(): CompoundBinaryTag = CompoundBinaryTag.from(tagMap)
}
