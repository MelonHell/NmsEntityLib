package ru.melonhell.nmsentitylib.app

import co.aikar.commands.BaseCommand
import co.aikar.commands.PaperCommandManager
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration

@Configuration
open class CommandBeanPostProcessor(
    private val paperCommandManager: PaperCommandManager
) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is BaseCommand) paperCommandManager.registerCommand(bean)
        return bean
    }
}