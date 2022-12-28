package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.horse

import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftHorse
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelHorseBukkitImpl(
    server: CraftServer,
    entity: NelHorseNmsImpl
) : CraftHorse(server, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelHorseNmsImpl
}