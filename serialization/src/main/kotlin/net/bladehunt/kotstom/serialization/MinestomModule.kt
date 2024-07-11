package net.bladehunt.kotstom.serialization

import java.util.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import net.bladehunt.kotstom.serialization.namespace.MaterialSerializer
import net.bladehunt.kotstom.serialization.namespace.NamespaceSerializer
import net.bladehunt.kotstom.serialization.point.BlockVecSerializer
import net.bladehunt.kotstom.serialization.point.PosSerializer
import net.bladehunt.kotstom.serialization.point.VecSerializer
import net.bladehunt.kotstom.serialization.uuid.UuidSerializer
import net.minestom.server.coordinate.BlockVec
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.item.Material
import net.minestom.server.utils.NamespaceID

val minestomModule = SerializersModule {
    polymorphic(Point::class) {
        subclass(BlockVec::class, BlockVecSerializer)
        subclass(Pos::class, PosSerializer)
        subclass(Vec::class, VecSerializer)
    }

    contextual(NamespaceID::class, NamespaceSerializer)
    contextual(Material::class, MaterialSerializer)
    contextual(UUID::class, UuidSerializer)
}
