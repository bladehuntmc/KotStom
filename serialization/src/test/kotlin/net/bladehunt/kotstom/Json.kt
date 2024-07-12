package net.bladehunt.kotstom

import kotlinx.serialization.json.Json
import net.bladehunt.kotstom.serialization.MinestomModule

val Json = Json { serializersModule = MinestomModule }
