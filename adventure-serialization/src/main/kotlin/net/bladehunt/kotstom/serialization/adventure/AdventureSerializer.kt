package net.bladehunt.kotstom.serialization.adventure

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.kyori.adventure.nbt.CompoundBinaryTag
import kotlin.reflect.KClass

sealed class AdventureSerializer(
    val encodeDefaults: Boolean = false,
    override val serializersModule: SerializersModule = EmptySerializersModule()
) : SerialFormat {
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    fun <T : Any> encodeToCompound(clazz: KClass<T>, value: T): CompoundBinaryTag {
        val encoder = AdventureEncoder(this)
        encoder.encodeSerializableValue(clazz.serializer(), value)
        return encoder.asCompound()
    }

    inline fun <reified T : Any> encodeToCompound(value: T): CompoundBinaryTag =
        encodeToCompound(T::class, value)

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    fun <T : Any> decodeFromCompound(clazz: KClass<T>, compoundBinaryTag: CompoundBinaryTag): T =
        AdventureDecoder(this, compoundBinaryTag).decodeSerializableValue(clazz.serializer())

    inline fun <reified T : Any> decodeFromCompound(compoundBinaryTag: CompoundBinaryTag): T =
        decodeFromCompound(T::class, compoundBinaryTag)

    companion object Default : AdventureSerializer()
}

inline fun <reified T : Any> encodeToCompound(value: T): CompoundBinaryTag =
    AdventureSerializer.encodeToCompound(T::class, value)

inline fun <reified T : Any> decodeFromCompound(compoundBinaryTag: CompoundBinaryTag): T =
    AdventureSerializer.decodeFromCompound(T::class, compoundBinaryTag)
