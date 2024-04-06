package world.cepi.kstom.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import net.minestom.server.utils.math.IntRange

object IntRangeSerializer : KSerializer<IntRange> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("IntRange") {
        element<Int>("min")
        element<Int>("max")
    }

    override fun serialize(encoder: Encoder, value: IntRange) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.minimum)
            encodeIntElement(descriptor, 1, value.maximum)
        }
    }

    override fun deserialize(decoder: Decoder): IntRange {
        return decoder.decodeStructure(descriptor) {
            var min = 0
            var max = 0
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> min = decodeIntElement(descriptor, 0)
                    1 -> max = decodeIntElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            IntRange(min, max)
        }
    }
}