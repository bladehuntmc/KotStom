package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.sound.Sound

object SoundSourceSerializer : KSerializer<Sound.Source> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(Sound.Source::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Sound.Source =
        Sound.Source.valueOf(decoder.decodeString().uppercase())

    override fun serialize(encoder: Encoder, value: Sound.Source) = encoder.encodeString(value.name)
}
