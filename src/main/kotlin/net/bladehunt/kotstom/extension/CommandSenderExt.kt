package net.bladehunt.kotstom.extension

import net.bladehunt.kotstom.CommandManager
import net.minestom.server.command.CommandSender

/**
 * Executes a command as a CommandSender.
 *
 * @param command The raw command string (without the command prefix)
 * @return The execution result
 */
fun CommandSender.execute(command: String) = CommandManager.execute(this, command)
