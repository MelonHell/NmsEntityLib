package ru.melonhell.nmsentitylib.app

import ru.spliterash.springspigot.init.SpringSpigotPlugin

class NmsEntityLibPlugin : SpringSpigotPlugin() {
    override fun getAppClass() = NmsEntityLibApplication::class.java
}