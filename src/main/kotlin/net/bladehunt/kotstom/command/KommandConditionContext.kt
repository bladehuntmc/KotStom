package net.bladehunt.kotstom.command

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

/**
 * Context for `Kommand` conditions
 *
 * @property sender The sender of the command
 * @property label The alias used
 * @property player The sender as a player. This will throw a `ClassCastException` if the sender
 *   isn't a player
 * @author oglassdev
 */
data class KommandConditionContext(val sender: CommandSender, val label: String?) {
    val player: Player
        get() = sender as Player
}
