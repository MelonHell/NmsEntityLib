package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.armorstand

import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftArmorStand
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelArmorStandBukkitImpl(
    server: CraftServer,
    entity: NelArmorStandNmsImpl
) : CraftArmorStand(server, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelArmorStandNmsImpl
}