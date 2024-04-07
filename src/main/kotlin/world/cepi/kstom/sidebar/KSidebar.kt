package world.cepi.kstom.sidebar

import net.kyori.adventure.text.Component
import net.minestom.server.Viewable
import net.minestom.server.scoreboard.Sidebar

class KSidebar(val sidebar: Sidebar, title: Component): Viewable by sidebar {
    constructor(title: Component) : this(Sidebar(title), title)
    constructor() : this (Sidebar(Component.empty()), Component.empty())

    var title: Component = title
        set(value) {
            sidebar.setTitle(value)
            field = value
        }

    inline fun line(id: String, index: Int, block: () -> Component) = Sidebar.ScoreboardLine(id, block(), index)
    fun emptyLine(id: String, index: Int) = line(id, index, Component.empty())
    fun line(id: String, index: Int, component: Component) = Sidebar.ScoreboardLine(id, component, index)


    fun add(vararg lines: Sidebar.ScoreboardLine) = lines.forEach { line ->
        sidebar.createLine(line)
    }
    fun refresh(vararg lines: Sidebar.ScoreboardLine) = lines.forEach { line ->
        sidebar.updateLineContent(line.id, line.content)
    }
    fun clear() {
        sidebar.clear()
    }

    operator fun Sidebar.ScoreboardLine.unaryPlus() {
        sidebar.createLine(this)
    }
    inline fun update(clear: Boolean = true, block: KSidebar.() -> Unit) {
        if (clear) clear()
        block()
    }
}

inline fun sidebar(title: Component = Component.empty(), block: KSidebar.() -> Unit): KSidebar = KSidebar(Sidebar(title), title).apply(block)