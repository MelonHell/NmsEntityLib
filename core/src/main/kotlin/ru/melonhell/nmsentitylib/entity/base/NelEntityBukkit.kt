package ru.melonhell.nmsentitylib.entity.base

import org.bukkit.entity.Entity

interface NelEntityBukkit : Entity {
    val handle: NelEntityNms
    val passengersOffset: Double
        get() = handle.passengersOffset
    val passengersPlayerEyesOffset
        get() = handle.passengersOffset - 0.35 + 1.62
}