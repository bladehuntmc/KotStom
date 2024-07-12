package net.bladehunt.kotstom.serialization.component

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

open class MiniMessageSerializer(val miniMessage: MiniMessage) : KSerializer<Component> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(Component::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Component =
        miniMessage.deserialize(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Component) =
        encoder.encodeString(miniMessage.serialize(value))

    companion object Default : MiniMessageSerializer(MiniMessage.miniMessage())
}
