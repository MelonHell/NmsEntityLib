/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.snow_golem

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSnowman
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelSnowGolemBukkitImpl(
    entity: NelSnowGolemNmsImpl
) : CraftSnowman(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelSnowGolemNmsImpl
}