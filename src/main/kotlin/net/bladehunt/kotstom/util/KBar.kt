package net.bladehunt.kotstom.util

import java.util.concurrent.atomic.AtomicInteger
import net.kyori.adventure.text.Component
import net.minestom.server.Viewable
import net.minestom.server.entity.Player
import net.minestom.server.scoreboard.Sidebar
import net.minestom.server.scoreboard.Sidebar.ScoreboardLine

/**
 * A Sidebar with automatic line placements
 *
 * @param title The original title of the Sidebar
 * @author oglassdev
 */
class KBar(title: Component = Component.empty()) : Viewable {
    private val sidebar: Sidebar = Sidebar(title)
    private val lineId: AtomicInteger = AtomicInteger(-1)

    inner class Line(display: Component = Component.empty()) {
        var id: String = lineId.getAndDecrement().toString()
            set(value) {
                field = value
                update(inner, ScoreboardLine(value, inner.content, inner.line))
            }

        var display: Component = display
            set(value) {
                field = value
                update(inner, ScoreboardLine(inner.id, value, inner.line))
            }

        var line: Int = (sidebar.lines.minOfOrNull { it.line } ?: 0) - 1
            set(value) {
                field = value
                update(inner, ScoreboardLine(inner.id, inner.content, value))
            }

        var isVisible = true
            set(value) {
                field = value
                update(inner, inner)
            }

        private var inner = ScoreboardLine(id, display, line).apply(sidebar::createLine)

        private fun update(old: ScoreboardLine?, new: ScoreboardLine) {
            old?.id?.apply(sidebar::removeLine)
            if (isVisible) sidebar.createLine(new)
            inner = new
        }
    }

    private val lines: ArrayList<Line> = arrayListOf()

    fun addLine(line: Line) {
        lines.add(line)
    }

    fun setTitle(title: Component) {
        sidebar.setTitle(title)
    }

    override fun addViewer(player: Player): Boolean = sidebar.addViewer(player)

    override fun removeViewer(player: Player): Boolean = sidebar.removeViewer(player)

    override fun getViewers(): MutableSet<Player> = sidebar.viewers
}
