package world.cepi.kstom.sidebar

import net.kyori.adventure.text.Component
import net.minestom.server.Viewable
import net.minestom.server.scoreboard.Sidebar
import net.minestom.server.scoreboard.Sidebar.ScoreboardLine


class KSidebar(
    title: Component,
    val sidebar: Sidebar = Sidebar(title)
): Viewable by sidebar {

    inline fun line(id: String, index: Int, generationalLambda: () -> Component) = line(id, index, generationalLambda())

    fun line(id: String, index: Int, component: Component) = ScoreboardLine(id, component, index)

    fun emptyLine(id: String, index: Int) = line(id, index, Component.empty())

    fun add(vararg lines: ScoreboardLine) = lines.forEach { line ->
        sidebar.createLine(line)
    }

    fun refresh(vararg lines: ScoreboardLine) = lines.forEach { line ->
        sidebar.updateLineContent(line.id, line.content)
    }
}

fun sidebar(title: Component, lambda: KSidebar.() -> Unit): KSidebar {
    val sidebar = KSidebar(title)

    lambda(sidebar)

    return sidebar
}