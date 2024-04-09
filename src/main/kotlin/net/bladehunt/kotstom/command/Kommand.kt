package net.bladehunt.kotstom.command

import net.minestom.server.command.builder.Command

class Kommand(name: String, vararg aliases: String) : Command(name, *aliases) {
}