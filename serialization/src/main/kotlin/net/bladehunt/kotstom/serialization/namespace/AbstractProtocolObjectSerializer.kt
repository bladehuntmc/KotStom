package net.bladehunt.kotstom.serialization.namespace

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minestom.server.registry.StaticProtocolObject
import net.minestom.server.utils.NamespaceID

abstract class AbstractProtocolObjectSerializer<T : StaticProtocolObject>(serialName: String) :
    KSerializer<T> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(serialName, PrimitiveKind.STRING)

    protected abstract fun fromNamespaceId(namespaceID: NamespaceID): T?

    final override fun deserialize(decoder: Decoder): T =
        requireNotNull(fromNamespaceId(NamespaceID.from(decoder.decodeString())))

    final override fun serialize(encoder: Encoder, value: T) = encoder.encodeString(value.name())
}
