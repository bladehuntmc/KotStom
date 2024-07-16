package net.bladehunt.kotstom.serialization

import java.util.*
import kotlinx.serialization.modules.*
import net.bladehunt.kotstom.serialization.adventure.KeySerializer
import net.bladehunt.kotstom.serialization.adventure.SoundSerializer
import net.bladehunt.kotstom.serialization.adventure.SoundSourceSerializer
import net.bladehunt.kotstom.serialization.namespace.EntityTypeSerializer
import net.bladehunt.kotstom.serialization.namespace.MaterialSerializer
import net.bladehunt.kotstom.serialization.namespace.NamespaceSerializer
import net.bladehunt.kotstom.serialization.namespace.PotionEffectSerializer
import net.bladehunt.kotstom.serialization.point.BlockVecSerializer
import net.bladehunt.kotstom.serialization.point.PosSerializer
import net.bladehunt.kotstom.serialization.point.VecSerializer
import net.bladehunt.kotstom.serialization.uuid.StringUuidSerializer
import net.bladehunt.kotstom.serialization.uuid.UuidSerializer
import net.minestom.server.coordinate.Point

internal fun SerializersModuleBuilder.registerDefaultModules() {
    polymorphic(Point::class) {
        subclass(BlockVecSerializer)
        subclass(PosSerializer)
        subclass(VecSerializer)
    }

    contextual(BlockVecSerializer)
    contextual(PosSerializer)
    contextual(VecSerializer)

    contextual(NamespaceSerializer)
    contextual(MaterialSerializer)
    contextual(EntityTypeSerializer)
    contextual(PotionEffectSerializer)

    contextual(KeySerializer)
    contextual(SoundSerializer)
    contextual(SoundSourceSerializer)
}

val MinestomConfigModule = SerializersModule {
    registerDefaultModules()

    contextual(UUID::class, StringUuidSerializer)
}

val MinestomModule = SerializersModule {
    registerDefaultModules()

    contextual(UUID::class, UuidSerializer)
}
