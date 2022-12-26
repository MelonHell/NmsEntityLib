package ru.melonhell.nmsentitylib.app

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["ru.melonhell.nmsentitylib"])
open class NmsEntityLibApplication {
    @Bean
    open fun paperCommandManager(javaPlugin: JavaPlugin): PaperCommandManager = PaperCommandManager(javaPlugin)
}