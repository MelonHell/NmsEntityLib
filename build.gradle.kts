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
    depend = listOf("SpringSpigot")
    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4",
        "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4",
    )
}

allprojects {
    apply(plugin = "kotlin")

    group = "ru.melonhell"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.aikar.co/content/groups/aikar/")
        maven {
            url = uri("https://nexus.spliterash.ru/repository/all/")
            credentials {
                username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
                password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
            }
        }
    }

    dependencies {
        compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
        compileOnly("ru.spliterash:spring-spigot:1.0.5")
    }
}



configure(allprojects - project(":core")) {
    dependencies.api(project(":core"))
}

project(":nms").subprojects.forEach {
    rootProject.dependencies {
        api(project(it.path, "reobf"))
    }
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