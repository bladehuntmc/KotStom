package net.bladehunt.kotstom.extras.util

import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import net.kyori.adventure.text.Component
import net.minestom.server.Viewable
import net.minestom.server.entity.Player
import net.minestom.server.scoreboard.Sidebar

/**
 * A Sidebar with automatic line placements
 *
 * @param title The original title of the Sidebar
 * @author oglassdev
 */
class ReactiveSidebar(
    title: Component = Component.empty(),
    coroutineContext: CoroutineContext = Dispatchers.Default,
) : Viewable {
    private val sidebar: Sidebar = Sidebar(title)
    private val lastLine: AtomicInteger = AtomicInteger(-1)
    private val coroutineScope: CoroutineScope = CoroutineScope(coroutineContext + SupervisorJob())

    inner class Line(
        val line: Int = lastLine.getAndIncrement(),
        val id: String = line.toString(),
        val provider: Line.() -> Component,
    ) {
        private val tracked: MutableSet<Any> = hashSetOf()

        operator fun <T> StateFlow<T>.invoke(): T {
            if (tracked.contains(this)) return value
            coroutineScope.launch { collectLatest { sidebar.updateLineContent(id, provider()) } }
            tracked.add(this)
            return value
        }
    }

    fun setTitle(title: Component) {
        sidebar.setTitle(title)
    }

    operator fun Line.unaryPlus() {
        sidebar.createLine(
            Sidebar.ScoreboardLine(id, provider(), line, Sidebar.NumberFormat.blank())
        )
    }

    override fun addViewer(player: Player): Boolean = sidebar.addViewer(player)

    override fun removeViewer(player: Player): Boolean = sidebar.removeViewer(player)

    override fun getViewers(): MutableSet<Player> = sidebar.viewers
}
