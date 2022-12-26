package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.areaeffectcloud

import net.minecraft.world.entity.AreaEffectCloud
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftAreaEffectCloud
import ru.melonhell.nmsentitylib.core.NelCraftEntity
import ru.melonhell.nmsentitylib.core.NelNmsEntity

class NelCraftAreaEffectCloudImpl(
    server: CraftServer,
    entity: AreaEffectCloud
) : CraftAreaEffectCloud(server, entity), NelCraftEntity {
    override val handle: NelNmsEntity
        get() = entity as NelNmsAreaEffectCloudImpl

    override var updateInterval: Int
        get() = handle.updateInterval
        set(value) {
            handle.updateInterval = value
        }
}