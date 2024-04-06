package world.cepi.kstom.serializer

import kotlinx.serialization.Serializable
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity

@Serializable
class SerializableEntityFinder(
    val entityFinderString: String
) {
    fun get(): ArgumentEntity = ArgumentEntity(entityFinderString).singleEntity(false).onlyPlayers(false)
}