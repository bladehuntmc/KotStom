package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.bladehunt.kotstom.dsl.item.item
import net.bladehunt.kotstom.dsl.listen
import net.bladehunt.kotstom.extension.adventure.text
import net.bladehunt.kotstom.extension.set
import net.bladehunt.kotstom.util.EventNodeInventory
import net.minestom.server.event.inventory.InventoryItemChangeEvent
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.Material
import java.lang.ref.WeakReference

class InventoryTest :
    FunSpec({
        beforeAny { SERVER }
        test("Inventory's eventNode should clear") {
            var inventory: EventNodeInventory? =
                EventNodeInventory(InventoryType.CHEST_6_ROW, text("hello"))
            inventory!!.eventNode().listen<InventoryItemChangeEvent> { it.slot shouldBe 2 }
            inventory!![2] = item(Material.STONE)

            val ref = WeakReference(inventory)
            ref.get() shouldNotBe null

            inventory = null

            waitUntilCleared(ref)
        }
        test("Inventory event nodes should not call listeners for other inventories") {
            val inventory = EventNodeInventory(InventoryType.CHEST_6_ROW, text("hello"))

            val other = EventNodeInventory(InventoryType.CHEST_6_ROW, text("hello"))

            inventory.eventNode().listen<InventoryItemChangeEvent> { it.slot shouldBe 2 }
            inventory[2] = item(Material.STONE)
            other[3] = item(Material.STONE)
        }
    })
