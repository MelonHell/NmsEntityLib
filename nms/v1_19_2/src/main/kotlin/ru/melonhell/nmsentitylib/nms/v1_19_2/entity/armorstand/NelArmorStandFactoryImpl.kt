package ru.melonhell.nmsentitylib.nms.v1_19_2.entity.armorstand

import org.bukkit.World
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld
import org.bukkit.entity.EntityType
import org.bukkit.util.Vector
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.core.NelCraftEntity
import ru.melonhell.nmsentitylib.core.NelEntityFactory
import java.util.function.Consumer

@Component
class NelArmorStandFactoryImpl : NelEntityFactory {
    override fun spawn(world: World, vector: Vector, function: Consumer<NelCraftEntity>): NelCraftArmorStandImpl {
        val nmsWorld = (world as CraftWorld).handle
        val entity = NelNmsArmorStandImpl(nmsWorld, vector.x, vector.y, vector.z)
        val bukkitEntity = entity.bukkitEntity
        function.accept(bukkitEntity)
        nmsWorld.addFreshEntity(entity)
        entity.updateInterval = Int.MAX_VALUE
        return bukkitEntity
    }

    override val entityType = EntityType.ARMOR_STAND
}