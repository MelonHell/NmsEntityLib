/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.squid

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSquid
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelSquidBukkitImpl(
    entity: NelSquidNmsImpl
) : CraftSquid(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelSquidNmsImpl
}