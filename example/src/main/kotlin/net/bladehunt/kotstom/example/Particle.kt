package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.particle
import net.minestom.server.entity.Player
import net.minestom.server.particle.Particle

fun sendParticle(player: Player) {
    player.sendPacket(
        particle {
            particle = Particle.ASH
            location = player.position
        }
    )
}