package net.bladehunt.kotstom

import net.minestom.server.MinecraftServer.*
import net.minestom.server.advancements.AdvancementManager
import net.minestom.server.adventure.bossbar.BossBarManager
import net.minestom.server.command.CommandManager
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.entity.metadata.animal.tameable.WolfMeta
import net.minestom.server.entity.metadata.other.PaintingMeta
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.exception.ExceptionManager
import net.minestom.server.gamedata.tags.TagManager
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.block.BlockManager
import net.minestom.server.instance.block.banner.BannerPattern
import net.minestom.server.instance.block.jukebox.JukeboxSong
import net.minestom.server.item.armor.TrimMaterial
import net.minestom.server.item.armor.TrimPattern
import net.minestom.server.item.enchant.*
import net.minestom.server.listener.manager.PacketListenerManager
import net.minestom.server.message.ChatType
import net.minestom.server.monitoring.BenchmarkManager
import net.minestom.server.network.ConnectionManager
import net.minestom.server.network.packet.PacketParser
import net.minestom.server.network.packet.client.ClientPacket
import net.minestom.server.recipe.RecipeManager
import net.minestom.server.registry.DynamicRegistry
import net.minestom.server.scoreboard.TeamManager
import net.minestom.server.timer.SchedulerManager
import net.minestom.server.utils.nbt.BinaryTagSerializer
import net.minestom.server.world.DimensionType
import net.minestom.server.world.biome.Biome

inline val GlobalEventHandler: GlobalEventHandler
    get() = getGlobalEventHandler()

inline val PacketListenerManager: PacketListenerManager
    get() = getPacketListenerManager()

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

inline val ExceptionManager: ExceptionManager
    get() = getExceptionManager()

inline val ConnectionManager: ConnectionManager
    get() = getConnectionManager()

inline val BossBarManager: BossBarManager
    get() = getBossBarManager()

inline val ClientPacketParser: PacketParser<ClientPacket>
    get() = getPacketParser()

inline val AdvancementManager: AdvancementManager
    get() = getAdvancementManager()

inline val TagManager: TagManager
    get() = getTagManager()

inline val ChatTypeRegistry: DynamicRegistry<ChatType>
    get() = getChatTypeRegistry()

inline val DimensionTypeRegistry: DynamicRegistry<DimensionType>
    get() = getDimensionTypeRegistry()

inline val BiomeRegistry: DynamicRegistry<Biome>
    get() = getBiomeRegistry()

inline val DamageTypeRegistry: DynamicRegistry<DamageType>
    get() = getDamageTypeRegistry()

inline val TrimMaterialRegistry: DynamicRegistry<TrimMaterial>
    get() = getTrimMaterialRegistry()

inline val TrimPatternRegistry: DynamicRegistry<TrimPattern>
    get() = getTrimPatternRegistry()

inline val BannerPatternRegistry: DynamicRegistry<BannerPattern>
    get() = getBannerPatternRegistry()

inline val WolfVariantRegistry: DynamicRegistry<WolfMeta.Variant>
    get() = getWolfVariantRegistry()

inline val EnchantmentRegistry: DynamicRegistry<Enchantment>
    get() = getEnchantmentRegistry()

inline val PaintingVariantRegistry: DynamicRegistry<PaintingMeta.Variant>
    get() = getPaintingVariantRegistry()

inline val JukeboxSongRegistry: DynamicRegistry<JukeboxSong>
    get() = getJukeboxSongRegistry()

inline val EnchantmentLevelBasedValues: DynamicRegistry<BinaryTagSerializer<out LevelBasedValue>>
    get() = enchantmentLevelBasedValues()

inline val EnchantmentValueEffects: DynamicRegistry<BinaryTagSerializer<out ValueEffect>>
    get() = enchantmentValueEffects()

inline val EnchantmentEntityEffects: DynamicRegistry<BinaryTagSerializer<out EntityEffect>>
    get() = enchantmentEntityEffects()

inline val EnchantmentLocationEffects: DynamicRegistry<BinaryTagSerializer<out LocationEffect>>
    get() = enchantmentLocationEffects()
