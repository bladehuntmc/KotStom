package net.bladehunt.kotstom.serialization

import java.util.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import net.bladehunt.kotstom.serialization.namespace.EntityTypeSerializer
import net.bladehunt.kotstom.serialization.namespace.MaterialSerializer
import net.bladehunt.kotstom.serialization.namespace.NamespaceSerializer
import net.bladehunt.kotstom.serialization.namespace.PotionEffectSerializer
import net.bladehunt.kotstom.serialization.point.BlockVecSerializer
import net.bladehunt.kotstom.serialization.point.PosSerializer
import net.bladehunt.kotstom.serialization.point.VecSerializer
import net.bladehunt.kotstom.serialization.uuid.StringUuidSerializer
import net.bladehunt.kotstom.serialization.uuid.UuidSerializer
import net.minestom.server.coordinate.BlockVec
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.EntityType
import net.minestom.server.item.Material
import net.minestom.server.potion.PotionEffect
import net.minestom.server.utils.NamespaceID

internal fun SerializersModuleBuilder.registerDefaultModules() {
    polymorphic(Point::class) {
        subclass(BlockVec::class, BlockVecSerializer)
        subclass(Pos::class, PosSerializer)
        subclass(Vec::class, VecSerializer)
    }

    contextual(NamespaceID::class, NamespaceSerializer)
    contextual(Material::class, MaterialSerializer)
    contextual(EntityType::class, EntityTypeSerializer)
    contextual(PotionEffect::class, PotionEffectSerializer)
}

val MinestomConfigModule = SerializersModule {
    registerDefaultModules()

    contextual(UUID::class, StringUuidSerializer)
}

val MinestomModule = SerializersModule {
    registerDefaultModules()

    contextual(UUID::class, UuidSerializer)
}
