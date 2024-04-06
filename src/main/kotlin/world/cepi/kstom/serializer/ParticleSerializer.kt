package world.cepi.kstom.serializer

import kotlinx.serialization.encoding.Decoder
import net.minestom.server.particle.Particle

object ParticleSerializer : AbstractProtocolObjectSerializer<Particle>(Particle::class) {
    override fun deserialize(decoder: Decoder): Particle = Particle.fromNamespaceId(decoder.decodeString())!!
}