package ru.melonhell.nmsentitylib

import org.bukkit.Chunk
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.plugin.Plugin
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.spliterash.springspigot.listener.SpigotListener
import javax.annotation.PreDestroy

@SpigotListener
class EntitySaveService : Listener {
    private val entityMap = HashMap<ChunkPos, MutableList<NelEntityBukkit>>()
    private val entities = HashMap<Plugin, MutableList<NelEntityBukkit>>()

    @PreDestroy
    fun destroy() {
        entities.values.forEach { list -> list.forEach { entity -> entity.remove() } }
        entities.clear()
    }

    fun onCreate(plugin: Plugin, entity: NelEntityBukkit) {
        entities.computeIfAbsent(plugin) { mutableListOf() }.add(entity)
    }

    fun save(entity: NelEntityBukkit) {
        entityMap.computeIfAbsent(ChunkPos(entity.chunk)) { mutableListOf() }.add(entity)
    }

    @EventHandler
    fun onChunkLoad(event: ChunkLoadEvent) {
        val entityList = entityMap.remove(ChunkPos(event.chunk)) ?: return
        entityList.forEach { it.handle.load() }
    }

    @EventHandler
    fun onPluginDisable(event: PluginDisableEvent) {
        val entityList = entities.remove(event.plugin) ?: return
        entityList.forEach { it.remove() }
    }

    data class ChunkPos(
        val x: Int,
        val z: Int,
        val world: World
    ) {
        constructor(chunk: Chunk) : this(chunk.x, chunk.z, chunk.world)
    }
}