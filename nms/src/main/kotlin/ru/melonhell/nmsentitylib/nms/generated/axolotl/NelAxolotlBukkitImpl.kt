/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.axolotl

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAxolotl
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelAxolotlBukkitImpl(
    entity: NelAxolotlNmsImpl
) : CraftAxolotl(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelAxolotlNmsImpl
}