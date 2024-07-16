package net.bladehunt.kotstom

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.encodeToString
import net.kyori.adventure.sound.Sound
import net.minestom.server.sound.SoundEvent

class AdventureTest :
    FunSpec({
        test("sounds should serialize") {
            val sound = Sound.sound().type(SoundEvent.BLOCK_MUD_HIT).pitch(1.2f).build()

            val encoded = Json.encodeToString(sound)

            Json.decodeFromString<Sound>(encoded) shouldBe sound
        }
    })
