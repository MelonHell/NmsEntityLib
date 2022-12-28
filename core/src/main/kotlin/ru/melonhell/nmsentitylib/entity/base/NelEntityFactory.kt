package ru.melonhell.nmsentitylib.entity.base

import org.bukkit.World
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.util.Vector
import java.util.function.Consumer

interface NelEntityFactory {
    fun spawn(
        world: World,
        vector: Vector,
        reason: CreatureSpawnEvent.SpawnReason = CreatureSpawnEvent.SpawnReason.CUSTOM,
        function: Consumer<NelEntityBukkit> = Consumer { }
    ): NelEntityBukkit

    val entityType: EntityType
}