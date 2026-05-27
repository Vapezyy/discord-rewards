plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "xyz.vapezyy"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.okaeri.cloud/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("net.dv8tion:JDA:6.3.1")  {
        exclude("opus-java", "opus-java")
    }
    val okaeriConfigsVersion = "6.1.0-beta.1"
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:$okaeriConfigsVersion")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:$okaeriConfigsVersion")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

val pluginName = "discord-rewards"
val pluginPackage = "xyz.vapezyy.discordrewards"

bukkit {
    main = "$pluginPackage.DiscordRewardsPlugin"
    name = pluginName
    apiVersion = "1.21"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.shadowJar {
    archiveFileName = "$pluginName v${project.version}.jar"

    listOf(
        "dev.rollczi.litecommands"
    ).forEach { relocate(it, "$pluginPackage.libs.$it") }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
    options.release = 21
}