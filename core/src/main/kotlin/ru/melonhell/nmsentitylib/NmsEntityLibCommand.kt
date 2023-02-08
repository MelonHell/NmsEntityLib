package ru.melonhell.nmsentitylib

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit

@Component
@CommandAlias("nel")
class NmsEntityLibCommand(
    private val nmsEntityLib: NmsEntityLib,
    private val javaPlugin: JavaPlugin,
    private val testShit: TestShit
) : BaseCommand() {

    private var last: Entity? = null

    @Subcommand("spawn")
    fun spawn(player: Player, @Default("armor_stand") type: String) {
        val entityType = EntityType.valueOf(type.uppercase())
        last = nmsEntityLib.spawnEntity(player.location, entityType)
    }

    @Subcommand("metahack")
    fun metahack(player: Player) {
        (last as NelEntityBukkit).handle.disableMetaAutoUpdate = true
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

    @Subcommand("tphereasync")
    fun tphereacync(player: Player) {
        Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, Runnable {
            last!!.teleport(player.location)
            player.sendMessage(last!!.location.toString())
        })
    }

    @Subcommand("standarms")
    fun standarms(player: Player) {
        (last as ArmorStand).setArms(true)
    }

    @Subcommand("sit")
    fun sit(player: Player) {
        last!!.addPassenger(player)
    }

    @Subcommand("test")
    fun test(player: Player) {
        (last as NelEntityBukkit).handle.test()
    }

    @Subcommand("testupdint")
    fun testupdint(player: Player) {
        testShit.printUpdateIntervals(player)
    }
}