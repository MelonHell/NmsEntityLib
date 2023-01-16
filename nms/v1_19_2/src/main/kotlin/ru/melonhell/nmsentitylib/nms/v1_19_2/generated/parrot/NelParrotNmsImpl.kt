/*
 * ЭТА ХУЙНЯ СГЕНЕРИНА ХУЙНЁЙ!!!
 * NmsEntityLib/lmao_shitty_codegen/main.py
 */
package ru.melonhell.nmsentitylib.nms.v1_19_2.generated.parrot

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.animal.Parrot
import net.minecraft.world.level.Level
import net.minecraft.world.level.entity.EntityInLevelCallback
import ru.melonhell.nmsentitylib.EntitySaveService
import ru.melonhell.nmsentitylib.entity.base.NelEntityNms
import ru.melonhell.nmsentitylib.nms.v1_19_2.ProxiedEntityInLevelCallback
import ru.melonhell.nmsentitylib.nms.v1_19_2.ReflectionUtils.broadcast
import ru.melonhell.nmsentitylib.nms.v1_19_2.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.ReflectionUtils.updateInterval
import ru.melonhell.nmsentitylib.utils.SchedulerUtils

class NelParrotNmsImpl(
    world: Level,
    x: Double,
    y: Double,
    z: Double,
    private val saveService: EntitySaveService,
    private val schedulerUtils: SchedulerUtils,
) : Parrot(EntityType.PARROT, world), NelEntityNms {
    override var disableMetaAutoUpdate: Boolean = false

    init {
        setPos(x, y, z)
    }

    override fun spawn() {
        level.addFreshEntity(this, (this as Entity).spawnReason)
        tracker?.serverEntity?.updateInterval = Int.MAX_VALUE
    }

    private val bukkit = NelParrotBukkitImpl(this)
    override fun getBukkitEntity() = bukkit

    // полезности
    override val passengersOffset: Double get() = passengersRidingOffset

    private fun broadcastPacket(packet: Packet<*>) {
        tracker?.serverEntity?.broadcast?.accept(packet)
    }

    // сохранение и загрузка при отгрузке/загрузке чанка
    override fun setLevelCallback(changeListener: EntityInLevelCallback) {
        super.setLevelCallback(ProxiedEntityInLevelCallback(changeListener, this, saveService, schedulerUtils))
    }

    override fun load() {
        if (removalReason != RemovalReason.UNLOADED_TO_CHUNK) return
        unsetRemoved()
        spawn()
    }

    // асинхронный телепорт
    override fun moveTo(x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        super.moveTo(x, y, z, yaw, pitch)
        broadcastPacket(ClientboundTeleportEntityPacket(this))
    }

    // хак для метадаты
    private val emptyEntityData = SynchedEntityData(this)
    private val realEntityData: SynchedEntityData get() = super.getEntityData()
    private val metaPacket: ClientboundSetEntityDataPacket
        get() = ClientboundSetEntityDataPacket(id, realEntityData, true, true)

    override fun sendMetaChanges() = broadcastPacket(metaPacket)

    override fun getEntityData(): SynchedEntityData {
        if (disableMetaAutoUpdate) {
            val traceClassName = Thread.currentThread().stackTrace[2].className
            if (traceClassName == ServerEntity::class.java.name) return emptyEntityData
        }
        return super.getEntityData()
    }

    override fun startSeenByPlayer(player: ServerPlayer) {
        super.startSeenByPlayer(player)
        if (disableMetaAutoUpdate) player.connection.send(metaPacket)
    }

    // отключение всякой бесовщины
    override fun tick() = Unit // шоб не тикало

    override fun push(deltaX: Double, deltaY: Double, deltaZ: Double) = Unit // чтоб не ставилось велосити

    override fun checkDespawn() = Unit // шоб не деспавнилось когда не надо

    override fun isInvulnerable() = true // шоб неубиваемое
    override fun isInvulnerableTo(damageSource: DamageSource) = true // шоб неубиваемое

    override fun save(nbt: CompoundTag) = false // шоб в мир не сейвило
    override fun shouldBeSaved() = false  // шоб в мир не сейвило
}