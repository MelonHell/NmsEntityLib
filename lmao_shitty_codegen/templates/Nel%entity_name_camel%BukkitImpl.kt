/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.generated.%entity_name%

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.%craftbukkit_version%.CraftServer
import org.bukkit.craftbukkit.%craftbukkit_version%.entity.%bukkit_craft_class_name%
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

class Nel%entity_name_camel%BukkitImpl(
    entity: Nel%entity_name_camel%NmsImpl
) : %bukkit_craft_class_name%(Bukkit.getServer() as CraftServer, entity), NelEntityBukkit {
    override val handle
        get() = entity as Nel%entity_name_camel%NmsImpl
}