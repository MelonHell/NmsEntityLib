package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.areaeffectcloud

import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftAreaEffectCloud
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms

class NelAreaEffectCloudBukkitImpl(
    server: CraftServer,
    entity: NelAreaEffectCloudNmsImpl
) : CraftAreaEffectCloud(server, entity), NelEntityBukkit {
    override val handle: NelEntityNms
        get() = entity as NelAreaEffectCloudNmsImpl
}