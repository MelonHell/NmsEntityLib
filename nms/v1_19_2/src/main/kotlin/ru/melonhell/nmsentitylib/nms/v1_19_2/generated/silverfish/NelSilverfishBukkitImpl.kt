/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.silverfish

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftSilverfish
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelSilverfishBukkitImpl(
    entity: NelSilverfishNmsImpl
) : CraftSilverfish(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelSilverfishNmsImpl
}