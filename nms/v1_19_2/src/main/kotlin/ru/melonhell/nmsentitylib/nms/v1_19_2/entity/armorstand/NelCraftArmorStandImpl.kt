package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.armorstand

import net.minecraft.world.entity.decoration.ArmorStand
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftArmorStand
import ru.melonhell.nmsentitylib.core.NelCraftEntity

class NelCraftArmorStandImpl(server: CraftServer, entity: ArmorStand) : CraftArmorStand(server, entity), NelCraftEntity {
    override val handle
        get() = entity as NelNmsArmorStandImpl
    override var updateInterval: Int
        get() = handle.updateInterval
        set(value) {
            handle.updateInterval = value
        }
}