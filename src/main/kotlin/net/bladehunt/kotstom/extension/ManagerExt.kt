package net.bladehunt.kotstom.extension

import net.minestom.server.command.CommandManager
import net.minestom.server.command.builder.Command

fun CommandManager.register(vararg commands: Command) = commands.forEach(this::register)