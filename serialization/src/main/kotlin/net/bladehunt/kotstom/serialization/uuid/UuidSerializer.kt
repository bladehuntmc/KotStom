package net.bladehunt.kotstom.serialization.uuid

import java.util.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

object UuidSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("UUID") {
            element<Long>("leastSignificantBits")
            element<Long>("mostSignificantBits")
        }

    override fun deserialize(decoder: Decoder): UUID =
        decoder.decodeStructure(descriptor) {
            var leastSignificantBits: Long = 0
            var mostSignificantBits: Long = 0

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> leastSignificantBits = decodeLongElement(descriptor, 0)
                    1 -> mostSignificantBits = decodeLongElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            UUID(mostSignificantBits, leastSignificantBits)
        }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeStructure(descriptor) {
            encodeLongElement(descriptor, 0, value.leastSignificantBits)
            encodeLongElement(descriptor, 1, value.mostSignificantBits)
        }
    }
}
