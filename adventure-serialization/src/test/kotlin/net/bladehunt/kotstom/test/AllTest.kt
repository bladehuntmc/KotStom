package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable data class Everything(val listData: ListData, val data: Data, val poly: Poly)

class AllTest :
    FunSpec({
        test("everything should work") {
            val valueToSerialize =
                Everything(
                    ListData(listOf("abc", "def", "ghi"), listOf(0, 1, 2), listOf(0.1, 1.2, 2.4)),
                    Data(
                        Data.Inner("John Doe", "The Earth", 40),
                        1,
                        2,
                        3,
                        4.314159F,
                        5.314159,
                        Data.Inner("Jane Doe", "John's House", 39),
                        "Value",
                        true),
                    Poly.Wants("cracker"))

            val valueAsCompound = AdventureNbt.encodeToCompound(valueToSerialize)

            val decodedValue = AdventureNbt.decodeFromCompound<Everything>(valueAsCompound)

            valueToSerialize shouldBe decodedValue
        }
    })
