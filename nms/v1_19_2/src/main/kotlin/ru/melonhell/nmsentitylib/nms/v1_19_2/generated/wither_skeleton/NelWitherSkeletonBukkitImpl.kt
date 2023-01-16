/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.wither_skeleton

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftWitherSkeleton
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelWitherSkeletonBukkitImpl(
    entity: NelWitherSkeletonNmsImpl
) : CraftWitherSkeleton(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelWitherSkeletonNmsImpl
}