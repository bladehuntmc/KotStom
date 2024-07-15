package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable
data class Data(
    val firstInner: Inner,
    val int: Int,
    val short: Short,
    val long: Long,
    val float: Float,
    val double: Double,
    val secondInner: Inner,
    val string: String,
    val boolean: Boolean,
) {
    @Serializable data class Inner(val name: String, val address: String, val age: Int)
}

class ObjectSerializationTest :
    FunSpec({
        test("should have the same value before and after serialization") {
            val valueToSerialize =
                Data(
                    Data.Inner("John Doe", "The Earth", 40),
                    1,
                    2,
                    3,
                    4.314159F,
                    5.314159,
                    Data.Inner("Jane Doe", "John's House", 39),
                    "Value",
                    true)

            val valueAsCompound = AdventureNbt.encodeToCompound(valueToSerialize)

            val decodedValue = AdventureNbt.decodeFromCompound<Data>(valueAsCompound)

            valueToSerialize shouldBe decodedValue
        }
    })
