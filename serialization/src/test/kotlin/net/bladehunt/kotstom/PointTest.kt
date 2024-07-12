package net.bladehunt.kotstom

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Pos

class PointTest :
    FunSpec({
        test("pos should not have a type field without polymorphism") {
            val pos = Pos(0.5, 1.5, 0.5, 45f, 108f)

            Json.encodeToJsonElement(pos).jsonObject.contains("type") shouldNotBe true
        }
        test("pos should be the same with polymorphism") {
            val point: Point = Pos(0.5, 1.5, 0.5, 45f, 108f)

            Json.encodeToJsonElement(point).jsonObject["type"]!!.jsonPrimitive.content shouldBe
                Pos::class.simpleName
        }
    })
