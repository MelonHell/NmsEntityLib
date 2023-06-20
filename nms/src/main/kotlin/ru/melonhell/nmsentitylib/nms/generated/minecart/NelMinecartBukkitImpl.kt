/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.minecart

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartRideable
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelMinecartBukkitImpl(
    entity: NelMinecartNmsImpl
) : CraftMinecartRideable(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelMinecartNmsImpl
}