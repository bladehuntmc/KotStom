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

/** Shorthand utilities for MinecraftServer */
object Manager {
    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("PacketListenerManager"))
    inline val packetListener: PacketListenerManager
        get() = PacketListenerManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("ExceptionManager"))
    inline val exception: ExceptionManager
        get() = ExceptionManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("ConnectionManager"))
    inline val connection: ConnectionManager
        get() = ConnectionManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("InstanceManager"))
    inline val instance: InstanceManager
        get() = InstanceManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("BlockManager"))
    inline val block: BlockManager
        get() = BlockManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("CommandManager"))
    inline val command: CommandManager
        get() = CommandManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("RecipeManager"))
    inline val recipe: RecipeManager
        get() = RecipeManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("TeamManager"))
    inline val team: TeamManager
        get() = TeamManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("SchedulerManager"))
    inline val scheduler: SchedulerManager
        get() = SchedulerManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("BenchmarkManager"))
    inline val benchmark: BenchmarkManager
        get() = BenchmarkManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("DimensionTypeManager"))
    inline val dimensionType: DimensionTypeManager
        get() = DimensionTypeManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("BiomeManager"))
    inline val biome: BiomeManager
        get() = BiomeManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("AdvancementManager"))
    inline val advancement: AdvancementManager
        get() = AdvancementManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("BossBarManager"))
    inline val bossBar: BossBarManager
        get() = BossBarManager

    @Deprecated("As of 0.1.0-beta.2", ReplaceWith("GlobalEventHandler"))
    inline val globalEvent: GlobalEventHandler
        get() = GlobalEventHandler
}

inline val PacketListenerManager: PacketListenerManager
    get() = getPacketListenerManager()
inline val ExceptionManager: ExceptionManager
    get() = getExceptionManager()
inline val ConnectionManager: ConnectionManager
    get() = getConnectionManager()
inline val InstanceManager: InstanceManager
    get() = getInstanceManager()
inline val BlockManager: BlockManager
    get() = getBlockManager()
inline val CommandManager: CommandManager
    get() = getCommandManager()
inline val RecipeManager: RecipeManager
    get() = getRecipeManager()
inline val TeamManager: TeamManager
    get() = getTeamManager()
inline val SchedulerManager: SchedulerManager
    get() = getSchedulerManager()
inline val BenchmarkManager: BenchmarkManager
    get() = getBenchmarkManager()
inline val DimensionTypeManager: DimensionTypeManager
    get() = getDimensionTypeManager()
inline val BiomeManager: BiomeManager
    get() = getBiomeManager()
inline val AdvancementManager: AdvancementManager
    get() = getAdvancementManager()
inline val BossBarManager: BossBarManager
    get() = getBossBarManager()
inline val GlobalEventHandler: GlobalEventHandler
    get() = getGlobalEventHandler()
