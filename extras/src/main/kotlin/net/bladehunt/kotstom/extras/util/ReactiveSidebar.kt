package net.bladehunt.kotstom.extras.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.kyori.adventure.text.Component
import net.minestom.server.Viewable
import net.minestom.server.entity.Player
import net.minestom.server.scoreboard.Sidebar
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext

/**
 * A Sidebar with automatic line placements
 *
 * @param title The original title of the Sidebar
 * @author oglassdev
 */
class ReactiveSidebar(
    title: Component = Component.empty(),
    coroutineContext: CoroutineContext = Dispatchers.Default
) : Viewable, CoroutineScope by CoroutineScope(coroutineContext) {
    private val sidebar: Sidebar = Sidebar(title)
    private val lastLine: AtomicInteger = AtomicInteger(-1)

    inner class Line(
        val line: Int = lastLine.getAndIncrement(),
        val id: String = line.toString(),
        val provider: Line.() -> Component
    ) : CoroutineScope by CoroutineScope(coroutineContext) {
        private val tracked: ArrayList<Any> = arrayListOf()

        operator fun <T> StateFlow<T>.invoke(): T {
            if (tracked.contains(this)) return value
            launch { collectLatest { sidebar.updateLineContent(id, provider()) } }
            tracked.add(this)
            return value
        }
    }

    fun setTitle(title: Component) {
        sidebar.setTitle(title)
    }

    operator fun Line.unaryPlus() {
        sidebar.createLine(Sidebar.ScoreboardLine(id, provider(), line, Sidebar.NumberFormat.blank()))
    }

    override fun addViewer(player: Player): Boolean = sidebar.addViewer(player)

    override fun removeViewer(player: Player): Boolean = sidebar.removeViewer(player)

    override fun getViewers(): MutableSet<Player> = sidebar.viewers
}
