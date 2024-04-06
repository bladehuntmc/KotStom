package world.cepi.kstom.serializer

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

val MinestomSerializableModule = SerializersModule {
    contextual(BlockSerializer)
    contextual(ItemStackSerializer)
    contextual(PotionSerializer)
    contextual(NBTSerializer)
    contextual(PositionSerializer)
    contextual(SoundSerializer)
    contextual(VectorSerializer)
    contextual(IntRangeSerializer)
    contextual(UUIDSerializer)
    contextual(DurationSerializer)
    contextual(NamespaceIDSerializer)
    contextual(ComponentSerializer)
    contextual(BossBarSerializer)
    contextual(PermissionSerializer)
}

val MinestomJSON = Json {
    serializersModule = MinestomSerializableModule
    isLenient = true
    ignoreUnknownKeys = true
}