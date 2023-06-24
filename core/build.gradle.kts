plugins {
    `maven-publish`
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
}

java.withSourcesJar()
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.melonhell.nmsentitylib"
            artifactId = rootProject.name
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.melonhell.ru/public/")
            credentials {
                username = findProperty("MELONHELL_REPO_USR")?.toString()
                password = findProperty("MELONHELL_REPO_PSW")?.toString()
            }
        }
    }
}