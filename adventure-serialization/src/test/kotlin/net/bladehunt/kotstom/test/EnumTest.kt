package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Serializable
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt

@Serializable
enum class TestEnum {
    FIRST,
    SECOND,
    THIRD
}

@Serializable private data class Wrapper(val enum: TestEnum)

class EnumTest :
    FunSpec({
        test("enums should serialize properly") {
            val serialized = AdventureNbt.encodeToCompound(Wrapper(TestEnum.THIRD))

            AdventureNbt.decodeFromCompound<Wrapper>(serialized).enum shouldBe TestEnum.THIRD
        }
    })
