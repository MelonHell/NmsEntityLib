package ru.melonhell.nmsentitylib.nms.v1_19_2.entity

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import org.bukkit.World
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.util.Vector
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.entity.base.NelEntityFactory
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.utils.SchedulerUtils
import java.util.function.Consumer

abstract class AbstractNelEntityFactory(
    private val schedulerUtils: SchedulerUtils,
    private val saveService: EntitySaveService
) : NelEntityFactory {
    abstract fun createNms(nmsWorld: ServerLevel, x: Double, y: Double, z: Double, saveService: EntitySaveService): NelEntityNms

    override fun spawn(
        world: World,
        vector: Vector,
        reason: CreatureSpawnEvent.SpawnReason,
        function: Consumer<NelEntityBukkit>
    ): NelEntityBukkit {
        val nmsWorld = (world as CraftWorld).handle
        val entity = createNms(nmsWorld, vector.x, vector.y, vector.z, saveService)
        val bukkitEntity = entity.getBukkitEntity()
        function.accept(bukkitEntity)
        nmsWorld.addFreshEntity(entity as Entity, reason)
        entity.updateInterval = Int.MAX_VALUE
        return bukkitEntity
    }
}