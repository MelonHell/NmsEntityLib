package ru.melonhell.nmsentitylib

import org.bukkit.Chunk
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.spliterash.springspigot.listener.SpigotListener

@SpigotListener
class EntitySaveService : Listener {
    private val entityMap = HashMap<ChunkPos, MutableList<NelEntityBukkit>>()

    fun save(entity: NelEntityBukkit) {
        entityMap.computeIfAbsent(ChunkPos(entity.chunk)) { mutableListOf() }.add(entity)
    }

    @EventHandler
    fun onChunkLoad(event: ChunkLoadEvent) {
        val nelEntityList = entityMap.remove(ChunkPos(event.chunk)) ?: return
        nelEntityList.forEach { it.handle.load() }
    }

    data class ChunkPos(
        val x: Int,
        val z: Int,
        val world: World
    ) {
        constructor(chunk: Chunk) : this(chunk.x, chunk.z, chunk.world)
    }
}