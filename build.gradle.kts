plugins {
    kotlin("jvm") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

bukkit {
    name = "NmsEntityLib"
    main = "ru.melonhell.nmsentitylib.app.NmsEntityLibPlugin"
    apiVersion = "1.13"
    authors = listOf("MelonHell")
    depend = listOf("SpringSpigot", "GlobalLibraryLoader")
}

allprojects {
    apply(plugin = "kotlin")

    version = "1.0.3"

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.aikar.co/content/groups/aikar/")
        maven("https://repo.spliterash.ru/group/")
    }

    dependencies {
        compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
        compileOnly("ru.spliterash:spring-spigot:1.0.11")
    }
}



configure(allprojects - project(":core")) {
    dependencies.api(project(":core"))
}

dependencies {
    api(project(":nms", "reobf"))
}

tasks {
    shadowJar {
        relocate("co.aikar.commands", "ru.melonhell.nmsentitylib.libs.aikar.commands")
        relocate("co.aikar.locales", "ru.melonhell.nmsentitylib.libs.aikar.locales")
        archiveVersion.set("")
        archiveClassifier.set("")
    }
    assemble {
        dependsOn(shadowJar)
    }
    jar {
        enabled = false
    }
}