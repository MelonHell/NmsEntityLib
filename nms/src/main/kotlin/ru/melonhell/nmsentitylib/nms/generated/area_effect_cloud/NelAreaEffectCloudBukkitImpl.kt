/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.area_effect_cloud

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAreaEffectCloud
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class NelAreaEffectCloudBukkitImpl(
    entity: NelAreaEffectCloudNmsImpl
) : CraftAreaEffectCloud(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as NelAreaEffectCloudNmsImpl
}