package world.cepi.kstom.serializer

import kotlinx.serialization.encoding.Decoder
import net.minestom.server.sound.SoundEvent

object SoundEventSerializer : AbstractProtocolObjectSerializer<SoundEvent>(SoundEvent::class) {
    override fun deserialize(decoder: Decoder): SoundEvent = SoundEvent.fromNamespaceId(decoder.decodeString())!!
}