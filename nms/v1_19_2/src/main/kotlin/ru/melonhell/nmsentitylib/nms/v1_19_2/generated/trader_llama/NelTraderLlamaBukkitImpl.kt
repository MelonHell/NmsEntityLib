/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.trader_llama

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftTraderLlama
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelTraderLlamaBukkitImpl(
    entity: NelTraderLlamaNmsImpl
) : CraftTraderLlama(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelTraderLlamaNmsImpl
}