package net.bladehunt.kotstom.serialization.namespace

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minestom.server.utils.NamespaceID

object NamespaceSerializer : KSerializer<NamespaceID> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("NamespaceID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): NamespaceID =
        NamespaceID.from(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: NamespaceID) =
        encoder.encodeString(value.toString())
}
