package world.cepi.kstom.command.arguments

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.item.Material
import net.minestom.server.utils.NamespaceID

class ArgumentMaterial(id: String) : Argument<Material>(id) {
    override fun parse(sender: CommandSender, input: String): Material {
        val split = input.split(":")
        return Material.fromNamespaceId(
            NamespaceID.from(
                if (split.size > 1) split.first() else "minecraft",
                split.getOrNull(2) ?: split.first()
            )
        ) ?: throw ArgumentSyntaxException("Material was not found", input, 0)
    }

    override fun parser(): String = "minecraft:item_stack"
}