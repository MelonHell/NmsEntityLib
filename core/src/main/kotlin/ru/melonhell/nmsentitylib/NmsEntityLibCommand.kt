package ru.melonhell.nmsentitylib

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

@Component
@CommandAlias("nel")
class NmsEntityLibCommand(
    private val nmsEntityLib: NmsEntityLib
) : BaseCommand() {

    var last: NelEntityBukkit? = null

    @Subcommand("spawn")
    fun spawn(player: Player, @Default("armor_stand") type: String) {
        val entityType = EntityType.valueOf(type.uppercase())
        player.sendMessage("entityType $entityType")
        last = nmsEntityLib.spawnEntity(player.location, entityType)
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

    @Subcommand("eyeloc")
    fun eyeloc(player: Player) {
        player.sendMessage("eyeHeight " + player.eyeHeight)
        player.sendMessage("eyeLocation " + (player.eyeLocation.y - 63.0))
        player.sendMessage("passengersRidingOffset " + last!!.passengersPlayerEyesOffset)
    }

    @Subcommand("sit")
    fun sit(player: Player) {
        last!!.addPassenger(player)
    }
}