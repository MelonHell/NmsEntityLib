package ru.melonhell.nmsentitylib

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.entity.base.NelEntityFactory
import java.util.function.Consumer
import java.util.stream.Collectors

@Component
class NmsEntityLib(
    nelEntityFactoryList: List<NelEntityFactory>,
    private val saveService: EntitySaveService,
    private val javaPlugin: JavaPlugin,
) {
    private val map: Map<EntityType, NelEntityFactory> =
        nelEntityFactoryList.stream().collect(Collectors.toMap({ it.entityType }, { it }))

    @JvmOverloads
    fun spawnEntity(
        location: Location,
        entityType: EntityType,
        reason: CreatureSpawnEvent.SpawnReason = CreatureSpawnEvent.SpawnReason.CUSTOM,
        function: Consumer<NelEntityBukkit> = Consumer { },
        plugin: Plugin? = null
    ): NelEntityBukkit {
        val world = location.world ?: throw NullPointerException("location.world не может быть null")
        val nelEntityFactory = map[entityType] ?: throw RuntimeException("Фабрика для $entityType не найдена")
        val entity = nelEntityFactory.spawn(world, location.toVector(), reason, function)
        saveService.onCreate(plugin ?: javaPlugin, entity)
        return entity
    }
}