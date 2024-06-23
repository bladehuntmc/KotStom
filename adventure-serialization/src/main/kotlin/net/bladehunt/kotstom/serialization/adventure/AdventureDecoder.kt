package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.internal.TaggedDecoder
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.CompoundBinaryTag
import net.kyori.adventure.nbt.ListBinaryTag

@OptIn(InternalSerializationApi::class)
@ExperimentalSerializationApi
internal class AdventureDecoder(
    private val adventureSerializer: AdventureSerializer,
    private val compoundBinaryTag: CompoundBinaryTag
) : TaggedDecoder<String>() {
    private var index = -1

    override val serializersModule: SerializersModule
        get() = adventureSerializer.serializersModule

    override fun decodeTaggedInt(tag: String): Int = compoundBinaryTag.getInt(tag)

    override fun decodeTaggedByte(tag: String): Byte = compoundBinaryTag.getByte(tag)

    override fun decodeTaggedShort(tag: String): Short = compoundBinaryTag.getShort(tag)

    override fun decodeTaggedLong(tag: String): Long = compoundBinaryTag.getLong(tag)

    override fun decodeTaggedFloat(tag: String): Float = compoundBinaryTag.getFloat(tag)

    override fun decodeTaggedDouble(tag: String): Double = compoundBinaryTag.getDouble(tag)

    override fun decodeTaggedBoolean(tag: String): Boolean = compoundBinaryTag.getBoolean(tag)

    override fun decodeTaggedChar(tag: String): Char = compoundBinaryTag.getString(tag)[0]

    override fun decodeTaggedString(tag: String): String = compoundBinaryTag.getString(tag)

    override fun decodeTaggedEnum(tag: String, enumDescriptor: SerialDescriptor): Int =
        compoundBinaryTag.getInt(tag)

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int =
        (compoundBinaryTag[popTag()] as ListBinaryTag).size()

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        if (currentTagOrNull == null) return this

        return when (descriptor.kind) {
            is StructureKind.LIST ->
                AdventureListDecoder(adventureSerializer, compoundBinaryTag.getList(currentTag))
            else ->
                AdventureDecoder(
                    adventureSerializer, compoundBinaryTag[currentTag] as CompoundBinaryTag)
        }
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        index++
        return if (index >= descriptor.elementsCount) CompositeDecoder.DECODE_DONE else index
    }

    private fun elementName(descriptor: SerialDescriptor, index: Int): String =
        descriptor.getElementName(index)

    override fun SerialDescriptor.getTag(index: Int): String {
        return elementName(this, index)
    }
}
