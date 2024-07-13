package net.bladehunt.kotstom.serialization.adventure

import kotlin.reflect.KClass
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.kyori.adventure.nbt.CompoundBinaryTag

open class AdventureNbt(
    val discriminator: String = "type",
    val shouldEncodeDefaults: Boolean = false,
    override val serializersModule: SerializersModule = EmptySerializersModule()
) : SerialFormat {
    data class Builder(
        var discriminator: String = "type",
        var shouldEncodeDefaults: Boolean = false,
        var serializersModule: SerializersModule? = null
    ) {
        fun build(): AdventureNbt =
            AdventureNbt(
                discriminator, shouldEncodeDefaults, serializersModule ?: EmptySerializersModule())
    }

    @OptIn(InternalSerializationApi::class)
    fun <T : Any> encodeToCompound(clazz: KClass<T>, value: T): CompoundBinaryTag {
        val encoder = AdventureCompoundEncoder(this)
        encoder.encodeSerializableValue(clazz.serializer(), value)
        return encoder.toBinaryTag()
    }

    inline fun <reified T : Any> encodeToCompound(value: T): CompoundBinaryTag =
        encodeToCompound(T::class, value)

    @OptIn(InternalSerializationApi::class)
    fun <T : Any> decodeFromCompound(clazz: KClass<T>, compoundBinaryTag: CompoundBinaryTag): T =
        AdventureCompoundDecoder(this, compoundBinaryTag)
            .decodeSerializableValue(clazz.serializer())

    inline fun <reified T : Any> decodeFromCompound(compoundBinaryTag: CompoundBinaryTag): T =
        decodeFromCompound(T::class, compoundBinaryTag)

    companion object Default : AdventureNbt()
}

inline fun AdventureNbt(block: (AdventureNbt.Builder) -> Unit): AdventureNbt =
    AdventureNbt.Builder().apply(block).build()
