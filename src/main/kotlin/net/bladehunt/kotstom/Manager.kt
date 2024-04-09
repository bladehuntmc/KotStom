package net.bladehunt.kotstom

import net.minestom.server.MinecraftServer.*
import net.minestom.server.advancements.AdvancementManager
import net.minestom.server.adventure.bossbar.BossBarManager
import net.minestom.server.command.CommandManager
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.exception.ExceptionManager
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.block.BlockManager
import net.minestom.server.listener.manager.PacketListenerManager
import net.minestom.server.monitoring.BenchmarkManager
import net.minestom.server.network.ConnectionManager
import net.minestom.server.recipe.RecipeManager
import net.minestom.server.scoreboard.TeamManager
import net.minestom.server.timer.SchedulerManager
import net.minestom.server.world.DimensionTypeManager
import net.minestom.server.world.biomes.BiomeManager

/**
 * Shorthand utilities for MinecraftServer
 */
object Manager {
    inline val packetListener: PacketListenerManager get() = getPacketListenerManager()
    inline val exception: ExceptionManager get() = getExceptionManager()
    inline val connection: ConnectionManager get() = getConnectionManager()
    inline val instance: InstanceManager get() = getInstanceManager()
    inline val block: BlockManager get() = getBlockManager()
    inline val command: CommandManager get() = getCommandManager()
    inline val recipe: RecipeManager get() = getRecipeManager()
    inline val team: TeamManager get() = getTeamManager()
    inline val scheduler: SchedulerManager get() = getSchedulerManager()
    inline val benchmark: BenchmarkManager get() = getBenchmarkManager()
    inline val dimensionType: DimensionTypeManager get() = getDimensionTypeManager()
    inline val biome: BiomeManager get() = getBiomeManager()
    inline val advancement: AdvancementManager get() = getAdvancementManager()
    inline val bossBar: BossBarManager get() = getBossBarManager()
    inline val globalEvent: GlobalEventHandler get() = getGlobalEventHandler()
}