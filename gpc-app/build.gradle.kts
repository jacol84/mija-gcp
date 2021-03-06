import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 */
//val ktor_version = "1.5.3"

plugins {
    id("pl.mija.gcp.kotlin-application-conventions")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}
repositories {
    mavenCentral()
    maven { setUrl("https://kotlin.bintray.com/ktor") }
    maven { setUrl("https://kotlin.bintray.com/kotlin-js-wrappers") }
    maven { setUrl("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

dependencies {
    implementation(Libs.Apache.commonsText)
    implementation(Libs.Ktor.core)
    implementation(Libs.Ktor.serverNetty)
    implementation(Libs.Ktor.webjars)
    implementation(Libs.Ktor.websockets)
    implementation(Libs.Ktor.html)
    implementation(Libs.Ktor.gson)
    implementation(Libs.Ktor.location)
    implementation(Libs.Jackson.databind)
    implementation(Libs.Jackson.xml)
    implementation(Libs.Jackson.kotlin)

//    implementation(Libs.Jetbrains.kotlinCss)
    implementation(Libs.Logback.classic)
    implementation(project(":gpc-utilities"))
    implementation(project(":gpc-utils:mija-validator"))
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClass.set("pl.mija.gcp.app.ApplicationKt")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClass
            )
        )
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
//    allWarningsAsErrors = true
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
}