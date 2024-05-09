package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import net.bladehunt.kotstom.extension.italic
import net.bladehunt.kotstom.extension.undecorate
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration

class AdventureTest : FunSpec({
    test("should unformat decorations") {
        val original = Component.text("Component").italic()
        original.decorations()[TextDecoration.ITALIC] shouldBe TextDecoration.State.TRUE

        val new = original.undecorate(TextDecoration.ITALIC)
        new.decorations()[TextDecoration.ITALIC] shouldBe TextDecoration.State.FALSE
    }
})