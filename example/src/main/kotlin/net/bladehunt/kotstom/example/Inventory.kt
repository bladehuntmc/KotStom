package net.bladehunt.kotstom.example

import net.bladehunt.kotstom.dsl.listenOnly
import net.bladehunt.kotstom.extension.set
import net.bladehunt.kotstom.util.EventNodeContainerInventory
import net.kyori.adventure.text.Component
import net.minestom.server.event.inventory.InventoryOpenEvent
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

val eventNodeInventory = EventNodeContainerInventory(
    InventoryType.CHEST_6_ROW,
    Component.text("Event Node Inventory")
).apply {
    set(1, 1, ItemStack.of(Material.WRITTEN_BOOK))
    eventNode().apply {
        listenOnly<InventoryOpenEvent> { event ->
            event.player.sendMessage("You opened the inventory")
        }
        listenOnly<InventoryPreClickEvent> { event ->
            event.player.sendMessage("You clicked ${event.clickInfo} in a EventNodeContainerInventory")
        }
    }
}