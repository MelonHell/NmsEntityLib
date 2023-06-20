/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.text_display

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTextDisplay
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelTextDisplayBukkitImpl(
    entity: NelTextDisplayNmsImpl
) : CraftTextDisplay(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelTextDisplayNmsImpl
}