package ru.melonhell.nmsentitylib

import it.unimi.dsi.fastutil.longs.Long2ObjectMap
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.plugin.Plugin
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.spliterash.springspigot.listener.SpigotListener
import java.util.function.Function
import javax.annotation.PreDestroy

@SpigotListener
class EntitySaveService : Listener {
    private val chunkMap = HashMap<World, Long2ObjectMap<MutableSet<NelEntityBukkit>>>()
    private val pluginMap = HashMap<Plugin, MutableSet<NelEntityBukkit>>()

    @PreDestroy
    fun destroy() {
        pluginMap.values.forEach { list -> list.forEach { entity -> entity.remove() } }
        pluginMap.clear()
    }

    fun onCreate(plugin: Plugin, entity: NelEntityBukkit) {
        pluginMap.computeIfAbsent(plugin) { hashSetOf() }.add(entity)
    }

    fun save(entity: NelEntityBukkit) {
        val location = entity.location
        val world = location.world!!
        val worldChunks = chunkMap.computeIfAbsent(world) { Long2ObjectOpenHashMap() }
        val chunkKey = chunkKey(location.x, location.z)

        val chunkEntities = worldChunks.computeIfAbsent(chunkKey, Function { hashSetOf() })
        chunkEntities.add(entity)
    }

    @EventHandler
    fun onChunkLoad(event: ChunkLoadEvent) {
        val chunkKey = event.chunk.chunkKey
        val worldChunkMap = chunkMap[event.world] ?: return
        val entityList = worldChunkMap.remove(chunkKey) ?: return

        entityList.forEach { it.handle.load() }
    }

    @EventHandler
    fun onPluginDisable(event: PluginDisableEvent) {
        val entityList = pluginMap.remove(event.plugin) ?: return
        entityList.forEach { it.remove() }
    }

    private fun chunkKey(locX: Number, locZ: Number): Long {
        val x = locX.toInt() shr 4;
        val z = locZ.toInt() shr 4;

        return x.toLong() and 0xffffffffL or (z.toLong() and 0xffffffffL shl 32)
    }
}