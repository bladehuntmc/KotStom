package net.bladehunt.kotstom.serialization.namespace

import net.minestom.server.item.Material
import net.minestom.server.utils.NamespaceID

object MaterialSerializer : AbstractProtocolObjectSerializer<Material>(Material::class) {
    override fun fromNamespaceId(namespaceID: NamespaceID): Material? =
        Material.fromNamespaceId(namespaceID)
}
