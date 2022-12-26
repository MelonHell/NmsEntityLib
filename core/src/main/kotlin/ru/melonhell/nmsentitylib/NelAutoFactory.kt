package ru.melonhell.nmsentitylib

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.core.NelCraftEntity
import ru.melonhell.nmsentitylib.core.NelEntityFactory
import java.util.function.Consumer
import java.util.stream.Collectors

@Component
class NelAutoFactory(
    nelEntityFactoryList: List<NelEntityFactory>
) {
    private val map: Map<EntityType, NelEntityFactory> =
        nelEntityFactoryList.stream().collect(Collectors.toMap({ it.entityType }, { it }))

    fun spawn(location: Location, entityType: EntityType, function: Consumer<NelCraftEntity> = Consumer {  }): NelCraftEntity {
        val nelEntityFactory = map[entityType] ?: throw RuntimeException("Фабрика для $entityType не найдена")
        return nelEntityFactory.spawn(location.world!!, location.toVector(), function)
    }
}