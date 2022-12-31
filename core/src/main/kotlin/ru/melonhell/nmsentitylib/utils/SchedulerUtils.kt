package ru.melonhell.nmsentitylib.utils

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component

@Component
class SchedulerUtils(
    private val javaPlugin: JavaPlugin
) {
    fun runSync(runnable: Runnable) {
        if (Bukkit.isPrimaryThread()) {
            runnable.run()
        } else {
            Bukkit.getScheduler().runTask(javaPlugin, runnable)
        }
    }
}