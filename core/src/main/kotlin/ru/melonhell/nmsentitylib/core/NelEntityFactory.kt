package ru.melonhell.nmsentitylib.core

import org.bukkit.World
import org.bukkit.entity.EntityType
import org.bukkit.util.Vector
import java.util.function.Consumer

interface NelEntityFactory {
    fun spawn(world: World, vector: Vector, function: Consumer<NelCraftEntity> = Consumer {  }): NelCraftEntity
    val entityType: EntityType
}