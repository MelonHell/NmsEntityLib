/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_4.generated.zombie_villager

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R3.CraftServer
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftVillagerZombie
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelZombieVillagerBukkitImpl(
    entity: NelZombieVillagerNmsImpl
) : CraftVillagerZombie(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelZombieVillagerNmsImpl
}