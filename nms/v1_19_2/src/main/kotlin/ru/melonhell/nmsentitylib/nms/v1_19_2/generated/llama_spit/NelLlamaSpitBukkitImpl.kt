/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.llama_spit

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftLlamaSpit
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelLlamaSpitBukkitImpl(
    entity: NelLlamaSpitNmsImpl
) : CraftLlamaSpit(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelLlamaSpitNmsImpl
}