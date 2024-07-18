package net.bladehunt.kotstom.extension

import net.minestom.server.inventory.AbstractInventory
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack

/**
 * Returns the row size of an inventory.
 *
 * @author oglassdev
 */
val InventoryType.rowSize: Int
    get() =
        when (this) {
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
            InventoryType.MERCHANT,
            InventoryType.ANVIL -> 3
            InventoryType.HOPPER -> 7
            InventoryType.SMITHING -> 4
            else -> 1
        }

/**
 * Indexing operator for getting an ItemStack at a position
 *
 * @author oglassdev
 */
operator fun AbstractInventory.get(slot: Int): ItemStack = this.getItemStack(slot)

/**
 * Indexing operator for setting an ItemStack at a position
 *
 * @author oglassdev
 */
operator fun AbstractInventory.set(slot: Int, itemStack: ItemStack) {
    this.setItemStack(slot, itemStack)
}

/**
 * Indexing operator for getting an ItemStack at an `x, y` position in an inventory
 *
 * @author oglassdev
 */
operator fun Inventory.get(x: Int, y: Int): ItemStack =
    this.getItemStack(y * inventoryType.rowSize + x)

/**
 * Indexing operator for setting an ItemStack at an `x, y` position in an inventory
 *
 * @author oglassdev
 */
operator fun Inventory.set(x: Int, y: Int, itemStack: ItemStack) {
    this.setItemStack(y * inventoryType.rowSize + x, itemStack)
}
