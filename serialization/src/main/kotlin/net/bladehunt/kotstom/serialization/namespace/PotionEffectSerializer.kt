package net.bladehunt.kotstom.serialization.namespace

import net.minestom.server.potion.PotionEffect
import net.minestom.server.utils.NamespaceID

object PotionEffectSerializer : AbstractProtocolObjectSerializer<PotionEffect>("PotionEffect") {
    override fun fromNamespaceId(namespaceID: NamespaceID): PotionEffect? =
        PotionEffect.fromNamespaceId(namespaceID)
}
