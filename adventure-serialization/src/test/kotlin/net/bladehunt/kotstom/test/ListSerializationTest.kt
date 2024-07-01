package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable
data class ListData(
    val stringList: List<String>,
    val intList: List<Int>,
    val doubleList: List<Double>
)

class ListSerializationTest :
    FunSpec({
        test("list should serialize properly") {
            val valueToSerialize =
                ListData(listOf("abc", "def", "ghi"), listOf(0, 1, 2), listOf(0.1, 1.2, 2.4))

            val valueAsCompound = AdventureNbt.encodeToCompound(valueToSerialize)

            val decodedValue = AdventureNbt.decodeFromCompound<ListData>(valueAsCompound)

            valueToSerialize shouldBe decodedValue
        }
    })
