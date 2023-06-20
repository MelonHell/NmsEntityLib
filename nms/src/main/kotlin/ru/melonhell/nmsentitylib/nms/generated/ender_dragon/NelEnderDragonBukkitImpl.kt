/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.ender_dragon

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragon
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelEnderDragonBukkitImpl(
    entity: NelEnderDragonNmsImpl
) : CraftEnderDragon(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelEnderDragonNmsImpl
}