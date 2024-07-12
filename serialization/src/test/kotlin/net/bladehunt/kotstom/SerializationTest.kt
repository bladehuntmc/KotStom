package net.bladehunt.kotstom

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.encodeToString
import net.minestom.server.entity.EntityType
import net.minestom.server.item.Material
import net.minestom.server.potion.PotionEffect

class SerializationTest :
    FunSpec({
        test("materials should serialize their namespace ID") {
            val material = Material.STONE
            val encoded = Json.encodeToString(material)

            material shouldBe Json.decodeFromString<Material>(encoded)
        }
        test("potions should serialize their namespace ID") {
            val potion = PotionEffect.HUNGER
            val encoded = Json.encodeToString(potion)

            potion shouldBe Json.decodeFromString<PotionEffect>(encoded)
        }
        test("entity types should serialize their namespace ID") {
            val entityType = EntityType.CAT
            val encoded = Json.encodeToString(entityType)

            entityType shouldBe Json.decodeFromString<EntityType>(encoded)
        }
    })
