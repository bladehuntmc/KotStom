package net.bladehunt.kotstom.command

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

data class KommandConditionContext(
    val sender: CommandSender,
    val label: String?
) {
    val player by lazy { sender as Player }
}