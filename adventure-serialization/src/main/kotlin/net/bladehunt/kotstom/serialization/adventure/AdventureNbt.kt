package net.bladehunt.kotstom.serialization.adventure

import kotlin.reflect.KClass
import kotlinx.serialization.*
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import net.kyori.adventure.nbt.CompoundBinaryTag

sealed class AdventureNbt(
    val discriminator: String = "type",
    override val serializersModule: SerializersModule = EmptySerializersModule()
) : SerialFormat {
    @OptIn(InternalSerializationApi::class)
    fun <T : Any> encodeToCompound(clazz: KClass<T>, value: T): CompoundBinaryTag {
        val encoder = AdventureCompoundEncoder(this)
        encoder.encodeSerializableValue(clazz.serializer(), value)
        return encoder.toBinaryTag()
    }

    inline fun <reified T : Any> encodeToCompound(value: T): CompoundBinaryTag =
        encodeToCompound(T::class, value)

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    fun <T : Any> decodeFromCompound(clazz: KClass<T>, compoundBinaryTag: CompoundBinaryTag): T =
        AdventureCompoundDecoder(this, compoundBinaryTag)
            .decodeSerializableValue(clazz.serializer())

    inline fun <reified T : Any> decodeFromCompound(compoundBinaryTag: CompoundBinaryTag): T =
        decodeFromCompound(T::class, compoundBinaryTag)

    companion object Default : AdventureNbt()
}
