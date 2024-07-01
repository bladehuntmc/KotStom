package net.bladehunt.kotstom.serialization.adventure.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import net.bladehunt.kotstom.serialization.adventure.AdventureNbt
import net.kyori.adventure.nbt.BinaryTag

@InternalSerializationApi
internal interface AdventureDecoder : Decoder {
    val adventureNbt: AdventureNbt

    override val serializersModule: SerializersModule
        get() = adventureNbt.serializersModule

    fun decodeBinaryTag(): BinaryTag
}
