package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.slime

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.monster.Slime
import net.minecraft.world.level.Level
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.app.NmsEntityLibPlugin
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.broadcast
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.updateInterval

class NelSlimeNmsImpl(
    world: Level,
    x: Double,
    y: Double,
    z: Double,
    private val saveService: EntitySaveService
) : Slime(EntityType.SLIME, world), NelEntityNms {
    init {
        setPos(x, y, z)
    }

    override fun save(nbt: CompoundTag): Boolean {
        saveService.save(bukkitEntity)
        return true
    }

    override fun tick() = Unit

    override fun moveTo(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(NmsEntityLibPlugin::class.java), Runnable {
            super.moveTo(x, y, z, yaw, pitch)
        })
        tracker?.serverEntity?.broadcast?.accept(ClientboundTeleportEntityPacket(this))
    }

    override fun getBukkitEntity() = NelSlimeBukkitImpl(Bukkit.getServer() as CraftServer, this)

    override var updateInterval
        get() = tracker?.serverEntity?.updateInterval ?: -1
        set(value) = tracker?.let { it.serverEntity.updateInterval = value } ?: Unit
    override val passengersOffset: Double
        get() = passengersRidingOffset

    override fun load() {
        if (removalReason == RemovalReason.UNLOADED_TO_CHUNK) {
            unsetRemoved()
            level.addFreshEntity(this as Entity, this.spawnReason)
        }
    }
}