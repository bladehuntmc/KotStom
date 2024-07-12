package net.bladehunt.kotstom.serialization.point

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import net.minestom.server.coordinate.BlockVec

object BlockVecSerializer : KSerializer<BlockVec> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(BlockVec::class.simpleName!!) {
            element<Double>("x")
            element<Double>("y")
            element<Double>("z")
        }

    override fun deserialize(decoder: Decoder): BlockVec =
        decoder.decodeStructure(descriptor) {
            var x: Int? = null
            var y: Int? = null
            var z: Int? = null

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> x = decodeIntElement(descriptor, 0)
                    1 -> y = decodeIntElement(descriptor, 1)
                    2 -> z = decodeIntElement(descriptor, 2)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            BlockVec(requireNotNull(x), requireNotNull(y), requireNotNull(z))
        }

    override fun serialize(encoder: Encoder, value: BlockVec) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.blockX())
            encodeIntElement(descriptor, 1, value.blockY())
            encodeIntElement(descriptor, 2, value.blockZ())
        }
    }
}
