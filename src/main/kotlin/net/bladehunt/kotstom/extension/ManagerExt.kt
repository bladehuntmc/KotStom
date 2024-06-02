package net.bladehunt.kotstom.extension

import net.minestom.server.command.CommandManager
import net.minestom.server.command.builder.Command

/**
 * Registers more than one command to the CommandManager
 *
 * @author oglassdev
 */
fun CommandManager.register(vararg commands: Command) = commands.forEach(this::register)
