package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.key.Key

object KeySerializer : KSerializer<Key> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(Key::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Key = Key.key(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Key) = encoder.encodeString(value.asString())
}
