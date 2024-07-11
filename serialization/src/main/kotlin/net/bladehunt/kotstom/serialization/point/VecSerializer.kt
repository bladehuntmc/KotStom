package net.bladehunt.kotstom.serialization.point

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import net.minestom.server.coordinate.Vec

object VecSerializer : KSerializer<Vec> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Vec") {
            element<Double>("x")
            element<Double>("y")
            element<Double>("z")
        }

    override fun deserialize(decoder: Decoder): Vec =
        decoder.decodeStructure(descriptor) {
            var x: Double? = null
            var y: Double? = null
            var z: Double? = null

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> x = decodeDoubleElement(descriptor, 0)
                    1 -> y = decodeDoubleElement(descriptor, 1)
                    2 -> z = decodeDoubleElement(descriptor, 2)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            Vec(requireNotNull(x), requireNotNull(y), requireNotNull(z))
        }

    override fun serialize(encoder: Encoder, value: Vec) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.x)
            encodeDoubleElement(descriptor, 1, value.y)
            encodeDoubleElement(descriptor, 2, value.z)
        }
    }
}
