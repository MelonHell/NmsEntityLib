package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.horse

import org.bukkit.World
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.util.Vector
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.entity.base.NelEntityFactory
import java.util.function.Consumer

@Component
class NelHorseFactoryImpl : NelEntityFactory {
    override fun spawn(
        world: World,
        vector: Vector,
        reason: CreatureSpawnEvent.SpawnReason,
        function: Consumer<NelEntityBukkit>
    ): NelHorseBukkitImpl {
        val nmsWorld = (world as CraftWorld).handle
        val entity = NelHorseNmsImpl(nmsWorld, vector.x, vector.y, vector.z)
        val bukkitEntity = entity.bukkitEntity
        function.accept(bukkitEntity)
        nmsWorld.addFreshEntity(entity, reason)
        entity.updateInterval = Int.MAX_VALUE
        return bukkitEntity
    }

    override val entityType = EntityType.HORSE
}