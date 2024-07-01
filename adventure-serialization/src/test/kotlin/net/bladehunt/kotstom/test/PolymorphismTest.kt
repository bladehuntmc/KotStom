package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable
sealed interface Poly {
    @Serializable data class Wants(val thing: String) : Poly

    @Serializable data class Needs(val amount: Int, val thing: String) : Poly
}

class PolymorphismTest :
    FunSpec({
        test("polymorphism should work") {
            val valueToSerialize = Poly.Wants("cracker")

            val serializedValue = AdventureNbt.encodeToCompound<Poly>(valueToSerialize)

            val decodedValue = AdventureNbt.decodeFromCompound<Poly>(serializedValue)

            valueToSerialize shouldBe decodedValue
        }
    })
