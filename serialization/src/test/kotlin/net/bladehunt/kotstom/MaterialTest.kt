package net.bladehunt.kotstom

import io.kotest.core.spec.style.FunSpec
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import net.minestom.server.item.Material

@Serializable data class ContextualMaterial(val material: @Contextual Material)

class MaterialTest :
    FunSpec({
        test("materials should serialize") { println(Json.encodeToString(Material.BOW)) }
        test("contextual materials should serialize") {
            println(Json.encodeToString(ContextualMaterial(Material.FEATHER)))
        }
    })
