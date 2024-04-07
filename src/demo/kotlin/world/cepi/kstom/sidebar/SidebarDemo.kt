package world.cepi.kstom.sidebar

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerStartFlyingEvent
import net.minestom.server.event.player.PlayerStopFlyingEvent
import world.cepi.kstom.event.listenOnly

fun attatchSidebar(player: Player) = sidebar(Component.text("Cool sidebar")) {
    fun flying(isFlying: Boolean) = line("flying",2) {
        Component.text("Flying: ", NamedTextColor.GRAY)
            .append(Component.text(isFlying, NamedTextColor.WHITE))
    }

    +flying(player.isFlying)
    +emptyLine("empty", 1)
    +line("website", 0, Component.text("Cool Website"))

    player.eventNode().apply {
        listenOnly<PlayerStartFlyingEvent> {
            refresh(flying(true))
        }
        listenOnly<PlayerStopFlyingEvent> {
            refresh(flying(false))
        }
    }
}
