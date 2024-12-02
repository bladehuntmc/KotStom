package net.bladehunt.kotstom.example.command

import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.defaultExecutor
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.particle
import net.minestom.server.command.builder.arguments.minecraft.registry.ArgumentParticle
import net.minestom.server.command.builder.arguments.number.ArgumentInteger

val ParticleCommand = kommand("particle") {
    val particleArg = ArgumentParticle("particle_id")
    val countArg = ArgumentInteger("count")

    defaultExecutor { sender.sendMessage("Please provide arguments.") }

    buildSyntax(particleArg) {
        onlyPlayers()

        executor {
            this.context
            player.instance.sendGroupedPacket(
                particle {
                    position = player.position
                    particle = particleArg()
                }
            )
        }
    }
    buildSyntax(particleArg, countArg) {
        onlyPlayers()
        executor {
            player.instance.sendGroupedPacket(
                particle {
                    position = player.position

                    particle = particleArg()
                    count = countArg()
                }
            )
        }
    }
}
