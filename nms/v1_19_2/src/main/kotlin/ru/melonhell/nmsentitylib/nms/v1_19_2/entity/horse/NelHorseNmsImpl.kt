package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.horse

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.animal.horse.Horse
import net.minecraft.world.level.Level
import net.minecraft.world.level.entity.EntityInLevelCallback
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ProxiedEntityInLevelCallback
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.broadcast
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.utils.ReflectionUtils.updateInterval
import ru.melonhell.nmsentitylib.utils.SchedulerUtils

class NelHorseNmsImpl(
    world: Level,
    x: Double,
    y: Double,
    z: Double,
    private val saveService: EntitySaveService,
    private val schedulerUtils: SchedulerUtils,
    ) : Horse(EntityType.HORSE, world), NelEntityNms {
    private val bukkit = NelHorseBukkitImpl(Bukkit.getServer() as CraftServer, this)
    private val emptyEntityData = SynchedEntityData(this)
    override var disableMetaAutoUpdate: Boolean = false

    init {
        setPos(x, y, z)
    }

    override fun setLevelCallback(changeListener: EntityInLevelCallback) {
        super.setLevelCallback(ProxiedEntityInLevelCallback(changeListener, this, saveService,schedulerUtils))
    }

    override fun shouldBeSaved() = false
    override fun save(nbt: CompoundTag) = false

    override fun tick() {
        detectEquipmentUpdates()
    }

    override fun moveTo(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        super.moveTo(x, y, z, yaw, pitch)
        tracker?.serverEntity?.broadcast?.accept(ClientboundTeleportEntityPacket(this))
    }

    override fun getEntityData(): SynchedEntityData {
        if (disableMetaAutoUpdate) {
            val traceClassName = Thread.currentThread().stackTrace[2].className
            if (traceClassName == ServerEntity::class.java.name) return emptyEntityData
        }
        return super.getEntityData()
    }

    override fun push(deltaX: Double, deltaY: Double, deltaZ: Double) = Unit

    private val realEntityData: SynchedEntityData get() = super.getEntityData()

    override fun sendMetaChanges() {
        tracker?.serverEntity?.broadcast?.accept(ClientboundSetEntityDataPacket(id, realEntityData, true, true))
    }

    override fun startSeenByPlayer(player: ServerPlayer) {
        super.startSeenByPlayer(player)
        if (disableMetaAutoUpdate)
            player.connection.send(ClientboundSetEntityDataPacket(id, realEntityData, true, true))
    }

    override fun getBukkitEntity() = bukkit

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

    override fun checkDespawn() = Unit
}