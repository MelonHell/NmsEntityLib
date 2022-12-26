pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "NmsEntityLib"

include(
    "core",
    "nms",
    "nms:v1_19_2"
)
