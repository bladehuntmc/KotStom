package net.bladehunt.kotstom.extension

import net.minestom.server.inventory.ContainerInventory
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack

val InventoryType.rowSize: Int
    get() = when (this) {
        InventoryType.CHEST_1_ROW,
        InventoryType.CHEST_2_ROW,
        InventoryType.CHEST_3_ROW,
        InventoryType.CHEST_4_ROW,
        InventoryType.CHEST_5_ROW,
        InventoryType.CHEST_6_ROW,
        InventoryType.SHULKER_BOX -> 9

        InventoryType.WINDOW_3X3,
        InventoryType.CRAFTER_3X3,
        InventoryType.CRAFTING,
        InventoryType.ANVIL -> 3

        InventoryType.HOPPER -> 7

        else -> 1
    }

operator fun Inventory.get(slot: Int): ItemStack = this.getItemStack(slot)
operator fun Inventory.set(slot: Int, itemStack: ItemStack) {
    this.setItemStack(slot, itemStack)
}

operator fun ContainerInventory.get(x: Int, y: Int): ItemStack = this.getItemStack(y * inventoryType.rowSize + x)
operator fun ContainerInventory.set(x: Int, y: Int, itemStack: ItemStack) {
    this.setItemStack(y * inventoryType.rowSize + x, itemStack)
}