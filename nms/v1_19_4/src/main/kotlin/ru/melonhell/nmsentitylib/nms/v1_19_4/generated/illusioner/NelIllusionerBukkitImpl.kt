/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_4.generated.illusioner

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R3.CraftServer
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftIllusioner
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelIllusionerBukkitImpl(
    entity: NelIllusionerNmsImpl
) : CraftIllusioner(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelIllusionerNmsImpl
}