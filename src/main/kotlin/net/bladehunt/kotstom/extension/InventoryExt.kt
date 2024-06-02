package net.bladehunt.kotstom.extension

import net.minestom.server.inventory.ContainerInventory
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.inventory.PlayerInventory
import net.minestom.server.inventory.click.Click
import net.minestom.server.item.ItemStack
import net.minestom.server.utils.inventory.PlayerInventoryUtils

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
operator fun Inventory.get(slot: Int): ItemStack = this.getItemStack(slot)

/**
 * Indexing operator for setting an ItemStack at a position
 *
 * @author oglassdev
 */
operator fun Inventory.set(slot: Int, itemStack: ItemStack) {
    this.setItemStack(slot, itemStack)
}

/**
 * Indexing operator for getting an ItemStack at an `x, y` position in an inventory
 *
 * @author oglassdev
 */
operator fun ContainerInventory.get(x: Int, y: Int): ItemStack =
    this.getItemStack(y * inventoryType.rowSize + x)

/**
 * Indexing operator for setting an ItemStack at an `x, y` position in an inventory
 *
 * @author oglassdev
 */
operator fun ContainerInventory.set(x: Int, y: Int, itemStack: ItemStack) {
    this.setItemStack(y * inventoryType.rowSize + x, itemStack)
}

/**
 * Helper method for getting an ItemStack from an `Inventory` or a `PlayerInventory`, depending on
 * the slot
 *
 * @author oglassdev
 */
fun getItemStack(slot: Int, inventory: Inventory, playerInventory: PlayerInventory): ItemStack {
    return if (slot < inventory.size) {
        inventory[slot]
    } else {
        playerInventory[PlayerInventoryUtils.protocolToMinestom(slot, inventory.size)]
    }
}

/**
 * Returns the slots involved in a `Click.Info`. If the `Click.Info` is a `Click.Info.HotbarSwap`,
 * then a list of the hotbarSlot and the clickedSlot is returned.
 *
 * @author oglassdev
 */
val Click.Info.slots: List<Int>
    get() {
        return when (this) {
            is Click.Info.CreativeSetItem -> listOf(this.slot)
            is Click.Info.Double -> listOf(this.slot)
            is Click.Info.DropSlot -> listOf(this.slot)
            is Click.Info.Left -> listOf(this.slot)
            is Click.Info.LeftShift -> listOf(this.slot)
            is Click.Info.Middle -> listOf(this.slot)
            is Click.Info.OffhandSwap -> listOf(this.slot)
            is Click.Info.Right -> listOf(this.slot)
            is Click.Info.RightShift -> listOf(this.slot)
            is Click.Info.HotbarSwap -> listOf(this.hotbarSlot, this.clickedSlot)
            is Click.Info.LeftDrag -> this.slots
            is Click.Info.MiddleDrag -> this.slots
            is Click.Info.RightDrag -> this.slots
            else -> emptyList()
        }
    }
