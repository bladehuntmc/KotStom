package net.bladehunt.kotstom.example.command

import kotlinx.coroutines.delay
import net.bladehunt.kotstom.coroutines.kommand.executorAsync
import net.bladehunt.kotstom.dsl.kommand.buildSyntax
import net.bladehunt.kotstom.dsl.kommand.defaultExecutor
import net.bladehunt.kotstom.dsl.kommand.kommand

val SuspendingCommand = kommand("suspending") {

    defaultExecutor { sender -> sender.sendMessage("You aren't a player.") }

    buildSyntax {
        onlyPlayers()

        executorAsync { sender ->
            repeat(11) { n ->
                sender.sendMessage("This is the $n-th time")
                delay(1000)
            }
        }
    }
}
