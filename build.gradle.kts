import org.gradle.kotlin.dsl.support.zipTo

plugins {
    kotlin("jvm") version "1.4.21"
    id("kr.entree.spigradle") version "2.2.3"
}

group = "net.netchoice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.heartpattern.kr/repository/maven-public/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.papermc:paperlib:1.0.6")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
}

spigot { // plugin.yml 설정
    authors = listOf("zeroday0619")
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {
    jar {
        from(
            shade.map {
                if (it.isDirectory)
                    it
                else
                    zipTree(it)
            }
        )
    }
}