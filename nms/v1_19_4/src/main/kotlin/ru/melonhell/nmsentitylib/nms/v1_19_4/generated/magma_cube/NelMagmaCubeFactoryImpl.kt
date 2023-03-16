/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_4.generated.magma_cube

import net.minecraft.server.level.ServerLevel
import org.bukkit.entity.EntityType
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.nms.v1_19_4.AbstractNelEntityFactory
import ru.melonhell.nmsentitylib.utils.SchedulerUtils

@Component
class NelMagmaCubeFactoryImpl(
    schedulerUtils: SchedulerUtils,
    saveService: EntitySaveService
) : AbstractNelEntityFactory(schedulerUtils, saveService) {
    override fun createNms(
        nmsWorld: ServerLevel,
        x: Double,
        y: Double,
        z: Double
    ) = NelMagmaCubeNmsImpl(nmsWorld, x, y, z, saveService, schedulerUtils)

    override val entityType = EntityType.MAGMA_CUBE
}