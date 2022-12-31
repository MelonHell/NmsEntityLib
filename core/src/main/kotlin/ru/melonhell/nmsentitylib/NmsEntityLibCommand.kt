package ru.melonhell.nmsentitylib

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component

@Component
@CommandAlias("nel")
class NmsEntityLibCommand(
    private val nmsEntityLib: NmsEntityLib,
    private val javaPlugin: JavaPlugin
) : BaseCommand() {

    private var last: Entity? = null

    @Subcommand("spawn")
    fun spawn(player: Player, @Default("armor_stand") type: String) {
        val entityType = EntityType.valueOf(type.uppercase())
        last = nmsEntityLib.spawnEntity(player.location, entityType)
    }

    @Subcommand("spawnbukkit")
    fun spawnbukkit(player: Player, @Default("armor_stand") type: String) {
        val entityType = EntityType.valueOf(type.uppercase())
        last = player.world.spawnEntity(player.location, entityType)
    }

    @Subcommand("stress")
    fun stress(player: Player, value: Int) {
        repeat(value) {
            nmsEntityLib.spawnEntity(player.location, EntityType.ARMOR_STAND)
        }
    }

    @Subcommand("tphere")
    fun tphere(player: Player) {
        last!!.teleport(player.location)
        player.sendMessage(last!!.location.toString())
    }

    @Subcommand("sit")
    fun sit(player: Player) {
        last!!.addPassenger(player)
    }
}