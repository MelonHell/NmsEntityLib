package ru.melonhell.nmsentitylib.entity.base

import org.bukkit.entity.Entity

interface NelEntityBukkit : Entity {
    val handle: NelEntityNms
    val passengersOffset: Double
        get() = handle.passengersOffset
    val passengersPlayerEyesOffset
        get() = handle.passengersOffset - 0.35 + 1.62

    var disableMetaAutoUpdate: Boolean
        get() = handle.disableMetaAutoUpdate
        set(value) {
            handle.disableMetaAutoUpdate = value
        }

    fun sendMetaChanges() = handle.sendMetaChanges()
}