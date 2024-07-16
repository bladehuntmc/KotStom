package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import net.bladehunt.kotstom.serialization.point.VecSerializer
import net.kyori.adventure.sound.Sound

object SoundSerializer : KSerializer<Sound> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(Sound::class.qualifiedName!!) {
            element("name", KeySerializer.descriptor)
            element("source", SoundSourceSerializer.descriptor)
            element<Float>("volume")
            element<Float>("pitch")
            element<Long>("seed", isOptional = true)
        }

    override fun deserialize(decoder: Decoder): Sound =
        decoder.decodeStructure(descriptor) {
            val sound: Sound.Builder = Sound.sound()

            while (true) {
                when (val index = decodeElementIndex(VecSerializer.descriptor)) {
                    0 -> sound.type(decodeSerializableElement(descriptor, 0, KeySerializer))
                    1 ->
                        sound.source(
                            decodeSerializableElement(descriptor, 1, SoundSourceSerializer))
                    2 -> sound.volume(decodeFloatElement(descriptor, 2))
                    3 -> sound.pitch(decodeFloatElement(descriptor, 3))
                    4 -> sound.seed(decodeLongElement(descriptor, 4))
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            sound.build()
        }

    override fun serialize(encoder: Encoder, value: Sound) =
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, KeySerializer, value.name())
            encodeSerializableElement(descriptor, 1, SoundSourceSerializer, value.source())
            encodeFloatElement(descriptor, 2, value.volume())
            encodeFloatElement(descriptor, 3, value.pitch())
            value
                .seed()
                .takeIf { it.isPresent }
                ?.let { encodeLongElement(descriptor, 4, it.asLong) }
        }
}
