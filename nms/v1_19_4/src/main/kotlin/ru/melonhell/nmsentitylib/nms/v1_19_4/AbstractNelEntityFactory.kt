package ru.melonhell.nmsentitylib.nms.v1_19_4

import net.minecraft.server.level.ServerLevel
import org.bukkit.World
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.util.Vector
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.entity.base.NelEntityFactory
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.utils.SchedulerUtils
import java.util.function.Consumer

abstract class AbstractNelEntityFactory(
    protected val schedulerUtils: SchedulerUtils,
    protected val saveService: EntitySaveService
) : NelEntityFactory {
    abstract fun createNms(nmsWorld: ServerLevel, x: Double, y: Double, z: Double): NelEntityNms

    override fun spawn(
        world: World,
        vector: Vector,
        reason: CreatureSpawnEvent.SpawnReason,
        function: Consumer<NelEntityBukkit>
    ): NelEntityBukkit {
        val nmsWorld = (world as CraftWorld).handle
        val entity = createNms(nmsWorld, vector.x, vector.y, vector.z)
        val bukkitEntity = entity.getBukkitEntity()
        function.accept(bukkitEntity)
        entity.spawn()
        return bukkitEntity
    }
}