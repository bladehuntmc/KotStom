package world.cepi.kstom.sidebar

import net.minestom.server.scoreboard.Sidebar

fun Sidebar.clear() {
    this.lines.iterator().forEach { this.removeLine(it.id) }
}