package net.bladehunt.kotstom.inventory

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.event.EventDispatcher
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventHandler
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryClickEvent
import net.minestom.server.event.inventory.InventoryPostClickEvent
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.ContainerInventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.inventory.click.Click
import net.minestom.server.inventory.click.ClickProcessors

class EventNodeContainerInventory(
    inventoryType: InventoryType,
    title: Component
) : ContainerInventory(inventoryType, title), EventHandler<InventoryEvent> {
    private val eventNode: EventNode<InventoryEvent> = EventNode.type("window.$windowId", EventFilter.INVENTORY)
    override fun handleClick(player: Player, info: Click.Info): List<Click.Change>? {
        val playerInventory = player.inventory

        val preClickEvent = InventoryPreClickEvent(playerInventory, this, player, info)
        EventDispatcher.call(preClickEvent)
        eventNode.call(preClickEvent)
        if (!preClickEvent.isCancelled) {
            val newInfo = preClickEvent.clickInfo
            val getter = Click.Getter(
                { slot: Int -> this.getItemStack(slot) },
                { slot: Int -> playerInventory.getItemStack(slot) },
                playerInventory.cursorItem,
                this.size
            )
            val processor = ClickProcessors.PROCESSORS_MAP.getOrDefault(inventoryType, ClickProcessors.GENERIC_PROCESSOR)
            val changes = processor.apply(newInfo, getter)

            val clickEvent = InventoryClickEvent(playerInventory, this, player, newInfo, changes)
            EventDispatcher.call(clickEvent)
            eventNode.call(clickEvent)

            if (!clickEvent.isCancelled) {
                val newChanges = clickEvent.changes

                apply(newChanges, player, this)

                val postClickEvent = InventoryPostClickEvent(player, this, newInfo, newChanges)
                EventDispatcher.call(postClickEvent)
                eventNode.call(postClickEvent)

                if (info != newInfo || changes != newChanges) {
                    this.update(player)
                }

                return newChanges
            }
        }

        if (this.isViewer(player)) {
            this.update(player)
        }
        return null
    }

    override fun eventNode(): EventNode<InventoryEvent> = eventNode
}