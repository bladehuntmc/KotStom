package net.bladehunt.kotstom.extras.util

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventHandler
import net.minestom.server.event.EventNode
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
    private val eventNode: EventNode<InventoryEvent> =
        MinecraftServer.getGlobalEventHandler().map(this, EventFilter.INVENTORY)

    override fun eventNode(): EventNode<InventoryEvent> = eventNode
}
