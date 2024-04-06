package world.cepi.kstom.serializer

import kotlinx.serialization.encoding.Decoder
import net.minestom.server.entity.EntityType

object EntityTypeSerializer : AbstractProtocolObjectSerializer<EntityType>(EntityType::class) {
    override fun deserialize(decoder: Decoder): EntityType = EntityType.fromNamespaceId(decoder.decodeString())
}