package net.bladehunt.kotstom.command

import net.minestom.server.command.builder.Command

/**
 * A `Command` implementation that gets generated from the `Kommand` builder DSL
 *
 * @author oglassdev
 */
class Kommand(name: String, vararg aliases: String) : Command(name, *aliases)
