package ru.melonhell.nmsentitylib.core

import org.bukkit.entity.Entity

interface NelCraftEntity : Entity {
    val handle: NelNmsEntity
    var updateInterval: Int
}