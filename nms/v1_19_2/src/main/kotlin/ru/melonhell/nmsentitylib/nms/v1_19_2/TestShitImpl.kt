package ru.melonhell.nmsentitylib.nms.v1_19_2

import net.minecraft.world.entity.Entity
import org.bukkit.entity.Player
import org.springframework.stereotype.Component
import ru.melonhell.nmsentitylib.TestShit
import ru.melonhell.nmsentitylib.entity.base.NelEntityBukkit
import ru.melonhell.nmsentitylib.nms.v1_19_2.ReflectionUtils.serverEntity
import ru.melonhell.nmsentitylib.nms.v1_19_2.ReflectionUtils.updateInterval

@Component
class TestShitImpl : TestShit {
    override fun printUpdateIntervals(player: Player) {
        player.getNearbyEntities(16.0, 16.0, 16.0).forEach {
            if (it is NelEntityBukkit) {
                player.sendMessage("suka " + (it.handle as Entity).tracker?.serverEntity?.updateInterval)
            }
        }
    }
}