package net.bladehunt.kotstom.serialization.namespace

import net.minestom.server.entity.EntityType
import net.minestom.server.utils.NamespaceID

object EntityTypeSerializer : AbstractProtocolObjectSerializer<EntityType>(EntityType::class) {
    override fun fromNamespaceId(namespaceID: NamespaceID): EntityType? =
        EntityType.fromNamespaceId(namespaceID)
}
