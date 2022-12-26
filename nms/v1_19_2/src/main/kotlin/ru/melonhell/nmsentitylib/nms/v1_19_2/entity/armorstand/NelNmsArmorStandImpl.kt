package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.armorstand

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.decoration.ArmorStand
import net.minecraft.world.level.Level
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import ru.melonhell.nmsentitylib.app.NmsEntityLibPlugin
import ru.melonhell.nmsentitylib.core.NelNmsEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.broadcast
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.updateInterval

class NelNmsArmorStandImpl(
    world: Level,
    x: Double,
    y: Double,
    z: Double
) : ArmorStand(world, x, y, z), NelNmsEntity {
    override fun save(nbt: CompoundTag): Boolean {
        return false
    }

    override fun tick() {
        updatePose()
        detectEquipmentUpdates()
    }

    override fun moveTo(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(NmsEntityLibPlugin::class.java), Runnable {
            super.moveTo(x, y, z, yaw, pitch)
        })
        tracker?.serverEntity?.broadcast?.accept(ClientboundTeleportEntityPacket(this))
    }

    override fun getBukkitEntity() = NelCraftArmorStandImpl(Bukkit.getServer() as CraftServer, this)

    override var updateInterval
        get() = tracker?.serverEntity?.updateInterval ?: -1
        set(value) = tracker?.let { it.serverEntity.updateInterval = value } ?: Unit
}