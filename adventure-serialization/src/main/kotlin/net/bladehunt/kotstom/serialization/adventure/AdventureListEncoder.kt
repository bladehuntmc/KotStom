package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.*

@OptIn(ExperimentalSerializationApi::class)
class AdventureListEncoder(
    private val adventureSerializer: AdventureSerializer,
    val consumeValue: ((ListBinaryTag) -> Unit)
) : AbstractEncoder() {
    private val tags = arrayListOf<BinaryTag>()

    override val serializersModule: SerializersModule
        get() = adventureSerializer.serializersModule

    override fun encodeBoolean(value: Boolean) {
        tags += if (value) ByteBinaryTag.ONE else ByteBinaryTag.ZERO
    }

    override fun encodeByte(value: Byte) {
        tags += ByteBinaryTag.byteBinaryTag(value)
    }

    override fun encodeChar(value: Char) = encodeString(value.toString())

    override fun encodeDouble(value: Double) {
        tags += DoubleBinaryTag.doubleBinaryTag(value)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) = encodeInt(index)

    override fun encodeFloat(value: Float) {
        tags += FloatBinaryTag.floatBinaryTag(value)
    }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder = this

    override fun encodeInt(value: Int) {
        tags += IntBinaryTag.intBinaryTag(value)
    }

    override fun encodeLong(value: Long) {
        tags += LongBinaryTag.longBinaryTag(value)
    }

    override fun encodeShort(value: Short) {
        tags += ShortBinaryTag.shortBinaryTag(value)
    }

    override fun encodeString(value: String) {
        tags += StringBinaryTag.stringBinaryTag(value)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        consumeValue(ListBinaryTag.from(tags))
    }
}
