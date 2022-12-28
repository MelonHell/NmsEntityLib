package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.areaeffectcloud

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.AreaEffectCloud
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import ru.melonhell.nmsentitylib.app.NmsEntityLibPlugin
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.broadcast
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.updateInterval

class NelAreaEffectCloudNmsImpl(
    world: Level,
    x: Double,
    y: Double,
    z: Double
) : AreaEffectCloud(EntityType.AREA_EFFECT_CLOUD, world), NelEntityNms {

    init {
        setPos(x, y, z)
    }

    override fun save(nbt: CompoundTag) = false

    override fun tick() = Unit

    override fun moveTo(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(NmsEntityLibPlugin::class.java), Runnable {
            super.moveTo(x, y, z, yaw, pitch)
        })
        tracker?.serverEntity?.broadcast?.accept(ClientboundTeleportEntityPacket(this))
    }

    override fun getBukkitEntity() = NelAreaEffectCloudBukkitImpl(Bukkit.getServer() as CraftServer, this)
    override var updateInterval
        get() = tracker!!.serverEntity.updateInterval
        set(value) {
            tracker!!.serverEntity.updateInterval = value
        }
    override val passengersOffset: Double
        get() = passengersRidingOffset
}