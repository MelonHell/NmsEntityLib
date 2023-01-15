package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.armorstand

import net.minecraft.server.level.ServerLevel
import org.bukkit.entity.EntityType
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.nms.v1_19_2.entity.AbstractNelEntityFactory
import ru.melonhell.nmsentitylib.utils.SchedulerUtils

@Component
class NelArmorStandFactoryImpl(
    schedulerUtils: SchedulerUtils,
    saveService: EntitySaveService
) : AbstractNelEntityFactory(schedulerUtils, saveService) {
    override fun createNms(
        nmsWorld: ServerLevel,
        x: Double,
        y: Double,
        z: Double
    ) = NelArmorStandNmsImpl(nmsWorld, x, y, z, saveService, schedulerUtils)

    override val entityType = EntityType.ARMOR_STAND
}