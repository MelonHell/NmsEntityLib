/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.vindicator

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftVindicator
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelVindicatorBukkitImpl(
    entity: NelVindicatorNmsImpl
) : CraftVindicator(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelVindicatorNmsImpl
}