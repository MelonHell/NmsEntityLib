package ru.melonhell.nmsentitylib.nms.v1_19_2

import net.minecraft.network.protocol.Packet
import net.minecraft.server.level.ChunkMap
import net.minecraft.server.level.ServerEntity
import java.util.function.Consumer

@Suppress("UNCHECKED_CAST")
object ReflectionUtils {
    private val serverEntityField = ChunkMap.TrackedEntity::class.java.getDeclaredField("b")
        .apply { isAccessible = true }
    var ChunkMap.TrackedEntity.serverEntity: ServerEntity
        get() = serverEntityField.get(this) as ServerEntity
        set(value) = serverEntityField.set(this, value)


    private val updateIntervalField = ServerEntity::class.java.getDeclaredField("e")
        .apply { isAccessible = true }
    var ServerEntity.updateInterval: Int
        get() = updateIntervalField.get(this) as Int
        set(value) = updateIntervalField.set(this, value)

    private val broadcastField = ServerEntity::class.java.getDeclaredField("g")
        .apply { isAccessible = true }
    var ServerEntity.broadcast: Consumer<Packet<*>>
        get() = broadcastField.get(this) as Consumer<Packet<*>>
        set(value) = broadcastField.set(this, value)
}