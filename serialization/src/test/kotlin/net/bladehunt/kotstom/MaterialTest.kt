package net.bladehunt.kotstom

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import net.minestom.server.item.Material

@Serializable data class ContextualMaterial(val material: @Contextual Material)

class MaterialTest :
    FunSpec({
        test("materials should serialize") {
            val material = Material.BOW

            val encoded = Json.encodeToString(material)

            Json.decodeFromString<Material>(encoded) shouldBe material
        }
        test("contextual materials should serialize") {
            val material = Material.FEATHER

            val encoded = Json.encodeToString(material)

            Json.decodeFromString<Material>(encoded) shouldBe material
        }
    })
