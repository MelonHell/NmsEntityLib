package ru.melonhell.nmsentitylib.test

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.NelAutoFactory
import ru.melonhell.nmsentitylib.core.NelCraftEntity

@Component
@CommandAlias("nel")
class NmsEntityLibCommand(
    private val nelAutoFactory: NelAutoFactory
) : BaseCommand() {

    var last: NelCraftEntity? = null

    @Subcommand("spawn")
    fun spawn(player: Player) {
        last = nelAutoFactory.spawn(player.location, EntityType.ARMOR_STAND)
    }

    @Subcommand("tp")
    fun tp(player: Player) {
        last!!.teleport(player.location)
        player.sendMessage(last!!.location.toString())
    }
}