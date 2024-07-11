package net.bladehunt.kotstom.serialization.point

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import net.minestom.server.coordinate.Pos

object PosSerializer : KSerializer<Pos> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Pos") {
            element<Double>("x")
            element<Double>("y")
            element<Double>("z")

            element<Float>("yaw")
            element<Float>("pitch")
        }

    override fun deserialize(decoder: Decoder): Pos =
        decoder.decodeStructure(descriptor) {
            var x: Double? = null
            var y: Double? = null
            var z: Double? = null

            var yaw: Float? = null
            var pitch: Float? = null

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> x = decodeDoubleElement(descriptor, 0)
                    1 -> y = decodeDoubleElement(descriptor, 1)
                    2 -> z = decodeDoubleElement(descriptor, 2)
                    3 -> yaw = decodeFloatElement(descriptor, 3)
                    4 -> pitch = decodeFloatElement(descriptor, 4)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            Pos(
                requireNotNull(x),
                requireNotNull(y),
                requireNotNull(z),
                requireNotNull(yaw),
                requireNotNull(pitch))
        }

    override fun serialize(encoder: Encoder, value: Pos) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.x)
            encodeDoubleElement(descriptor, 1, value.y)
            encodeDoubleElement(descriptor, 2, value.z)

            encodeFloatElement(descriptor, 3, value.yaw)
            encodeFloatElement(descriptor, 4, value.pitch)
        }
    }
}
