package world.cepi.kstom.serializer

import kotlinx.serialization.encoding.Decoder
import net.minestom.server.potion.PotionEffect

object PotionEffectSerializer : AbstractProtocolObjectSerializer<PotionEffect>(PotionEffect::class) {
    override fun deserialize(decoder: Decoder): PotionEffect = PotionEffect.fromNamespaceId(decoder.decodeString())!!
}