package net.bladehunt.kotstom.dsl

import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import net.minestom.server.network.packet.server.play.ParticlePacket
import net.minestom.server.particle.Particle

data class ParticleBuilder(
    var particle: Particle = Particle.ANGRY_VILLAGER,
    var longDistance: Boolean = false,
    var position: Point = Vec.ZERO,
    var offset: Point = Vec.ZERO,
    var particleData: Float = 0F,
    var count: Int = 1,
) {
    fun build(): ParticlePacket =
        ParticlePacket(particle, longDistance, position, offset, particleData, count)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ParticleBuilder

        if (particle != other.particle) return false
        if (longDistance != other.longDistance) return false
        if (position != other.position) return false
        if (offset != other.offset) return false
        if (particleData != other.particleData) return false
        if (count != other.count) return false

        return true
    }

    override fun hashCode(): Int {
        var result = particle.hashCode()
        result = 31 * result + longDistance.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + offset.hashCode()
        result = 31 * result + particleData.hashCode()
        result = 31 * result + count
        return result
    }
}

inline fun particle(block: ParticleBuilder.() -> Unit): ParticlePacket =
    ParticleBuilder().apply(block).build()
