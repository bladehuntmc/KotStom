package net.bladehunt.kotstom.serialization.namespace

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minestom.server.item.Material

object MaterialSerializer : KSerializer<Material> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Material", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Material =
        requireNotNull(Material.fromNamespaceId(NamespaceSerializer.deserialize(decoder)))

    override fun serialize(encoder: Encoder, value: Material) =
        NamespaceSerializer.serialize(encoder, value.namespace())
}
