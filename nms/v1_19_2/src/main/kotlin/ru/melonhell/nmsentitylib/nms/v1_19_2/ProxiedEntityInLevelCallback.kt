package ru.melonhell.nmsentitylib.nms.v1_19_2

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.entity.EntityInLevelCallback
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.utils.SchedulerUtils

class ProxiedEntityInLevelCallback(
    private val original: EntityInLevelCallback,
    private val entity: NelEntityNms,
    private val saveService: EntitySaveService,
    private val schedulerUtils: SchedulerUtils,
) : EntityInLevelCallback {
    override fun onMove() {
        schedulerUtils.runSync {
            if (!(entity as Entity).isRemoved) original.onMove()
        }
    }

    override fun onRemove(reason: Entity.RemovalReason) {
        if (reason == Entity.RemovalReason.UNLOADED_TO_CHUNK)
            saveService.save(entity.getBukkitEntity())
        original.onRemove(reason)
    }
}