package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.slime

import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSlime
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelSlimeBukkitImpl(
    server: CraftServer,
    entity: NelSlimeNmsImpl
) : CraftSlime(server, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelSlimeNmsImpl
}