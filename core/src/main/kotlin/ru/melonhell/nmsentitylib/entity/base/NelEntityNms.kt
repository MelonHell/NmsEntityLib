package ru.melonhell.nmsentitylib.entity.base

interface NelEntityNms {
    fun getBukkitEntity(): NelEntityBukkit
    var updateInterval: Int
    val passengersOffset: Double
    var disableMetaAutoUpdate: Boolean
    fun load()
    fun sendMetaChanges()
}