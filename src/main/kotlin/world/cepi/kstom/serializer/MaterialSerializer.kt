package world.cepi.kstom.serializer

import kotlinx.serialization.encoding.Decoder
import net.minestom.server.item.Material

object MaterialSerializer : AbstractProtocolObjectSerializer<Material>(Material::class) {
    override fun deserialize(decoder: Decoder): Material = Material.fromNamespaceId(decoder.decodeString())!!
}