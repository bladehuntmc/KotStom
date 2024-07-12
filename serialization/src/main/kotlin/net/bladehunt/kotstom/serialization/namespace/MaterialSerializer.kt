package net.bladehunt.kotstom.serialization.namespace

import net.minestom.server.item.Material
import net.minestom.server.utils.NamespaceID

object MaterialSerializer : AbstractProtocolObjectSerializer<Material>("Material") {
    override fun fromNamespaceId(namespaceID: NamespaceID): Material? =
        Material.fromNamespaceId(namespaceID)
}
