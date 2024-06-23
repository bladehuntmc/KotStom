package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.ListBinaryTag

@ExperimentalSerializationApi
internal class AdventureListDecoder(
    private val serializer: AdventureSerializer,
    private val nbtList: ListBinaryTag
) : AbstractDecoder() {
    private var index = -1
    private val list: ArrayList<Any> = arrayListOf()

    override val serializersModule: SerializersModule
        get() = serializer.serializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return when (descriptor.kind) {
            is StructureKind.LIST -> AdventureListDecoder(serializer, nbtList.getList(index))
            else -> AdventureDecoder(serializer, nbtList.getCompound(index))
        }
    }

    override fun decodeBoolean(): Boolean = nbtList.getByte(index) == 1.toByte()

    override fun decodeByte(): Byte = nbtList.getByte(index)

    override fun decodeChar(): Char = nbtList.getString(index)[0]

    override fun decodeDouble(): Double = nbtList.getDouble(index)

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int = nbtList.getInt(index)

    override fun decodeFloat(): Float = nbtList.getFloat(index)

    override fun decodeInline(descriptor: SerialDescriptor): Decoder = this

    override fun decodeInt(): Int = nbtList.getInt(index)

    override fun decodeLong(): Long = nbtList.getLong(index)

    override fun decodeShort(): Short = nbtList.getShort(index)

    override fun decodeString(): String = nbtList.getString(index)

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        index++
        return if (index == nbtList.size()) CompositeDecoder.DECODE_DONE else index
    }
}
