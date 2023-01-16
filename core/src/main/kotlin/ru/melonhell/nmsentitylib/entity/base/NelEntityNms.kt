package ru.melonhell.nmsentitylib.entity.base

interface NelEntityNms {
    fun getBukkitEntity(): NelEntityBukkit
    val passengersOffset: Double
    var disableMetaAutoUpdate: Boolean
    fun load()
    fun sendMetaChanges()
    fun test() = Unit
    fun spawn()
}