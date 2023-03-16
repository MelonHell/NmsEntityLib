/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_4.generated.cave_spider

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R3.CraftServer
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftCaveSpider
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelCaveSpiderBukkitImpl(
    entity: NelCaveSpiderNmsImpl
) : CraftCaveSpider(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelCaveSpiderNmsImpl
}