package world.cepi.kstom.command.arguments

import net.minestom.server.command.builder.arguments.ArgumentWord
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kstom.Manager

fun ArgumentPlayer(id: String) = ArgumentWord(id)
    .map { name: String -> Manager.connection.getOnlinePlayerByUsername(name) ?: throw ArgumentSyntaxException("Invalid player", name, 1) }
    .suggest(SuggestionIgnoreOption.IGNORE_CASE) { Manager.connection.onlinePlayers.map { it.username } }