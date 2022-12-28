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
            artifactId = rootProject.name
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}