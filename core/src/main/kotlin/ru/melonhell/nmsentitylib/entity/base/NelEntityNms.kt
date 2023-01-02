package ru.melonhell.nmsentitylib.entity.base

interface NelEntityNms {
    fun init(){}
    fun getBukkitEntity(): NelEntityBukkit
    var updateInterval: Int
    val passengersOffset: Double
    fun load()
}