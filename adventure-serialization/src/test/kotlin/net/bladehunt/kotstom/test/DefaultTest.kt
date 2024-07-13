package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldNotContain
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable data class Defaults(val first: String, val second: Int = 42)

class DefaultTest :
    FunSpec({
        test("default encoder does not encode defaults") {
            val compound = AdventureNbt.encodeToCompound(Defaults("qwerty"))
            compound.keySet() shouldNotContain "second"
        }
    })
