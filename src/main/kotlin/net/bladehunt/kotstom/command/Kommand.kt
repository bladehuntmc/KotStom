package net.bladehunt.kotstom.command

import net.minestom.server.command.builder.Command

/**
 * Kommand marker class for proper DSL support.
 *
 * @author oglassdev
 */
class Kommand(name: String, vararg aliases: String) : Command(name, *aliases)