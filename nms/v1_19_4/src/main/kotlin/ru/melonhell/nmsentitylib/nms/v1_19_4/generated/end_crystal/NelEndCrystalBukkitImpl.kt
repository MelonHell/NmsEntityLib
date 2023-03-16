/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_4.generated.end_crystal

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R3.CraftServer
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftEnderCrystal
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelEndCrystalBukkitImpl(
    entity: NelEndCrystalNmsImpl
) : CraftEnderCrystal(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelEndCrystalNmsImpl
}