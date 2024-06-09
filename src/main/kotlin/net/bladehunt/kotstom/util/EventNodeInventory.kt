package net.bladehunt.kotstom.util

import net.bladehunt.kotstom.GlobalEventHandler
import net.bladehunt.kotstom.dsl.listen
import net.kyori.adventure.text.Component
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventHandler
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.*
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

/**
 * A ContainerInventory with an `EventNode`. These `EventNode`s are never registered anywhere,
 * meaning that they are cleaned up properly.
 *
 * @param inventoryType The type of the inventory
 * @param title The inventory's title
 * @author oglassdev
 */
open class EventNodeInventory(inventoryType: InventoryType, title: Component) :
    Inventory(inventoryType, title), EventHandler<InventoryEvent> {
    private companion object {
        init {
            EventNode.type("EventNodeContainerInventory.Companion.eventNode", EventFilter.INVENTORY)
                .apply {
                    listen<InventoryPreClickEvent>(::handleEvent)
                    listen<InventoryClickEvent>(::handleEvent)
                    listen<InventoryCloseEvent>(::handleEvent)
                    listen<InventoryOpenEvent>(::handleEvent)
                    listen<InventoryItemChangeEvent>(::handleEvent)
                    GlobalEventHandler.addChild(this)
                }
        }

        private fun <T : InventoryEvent> handleEvent(event: T) {
            val inventory = event.inventory as? EventNodeInventory ?: return
            inventory.eventNode().call(event)
        }
    }

    private val eventNode: EventNode<InventoryEvent> =
        EventNode.type("window.$windowId", EventFilter.INVENTORY)

    override fun eventNode(): EventNode<InventoryEvent> = eventNode
}
