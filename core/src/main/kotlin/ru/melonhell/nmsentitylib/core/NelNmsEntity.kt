package ru.melonhell.nmsentitylib.core

interface NelNmsEntity {
    fun getBukkitEntity(): NelCraftEntity
    var updateInterval: Int
}