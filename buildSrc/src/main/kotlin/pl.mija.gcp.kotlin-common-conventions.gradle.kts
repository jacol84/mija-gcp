plugins {
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        implementation(Libs.Apache.commonsText)
        implementation(Libs.Jetbrains.kotlinStdLib)
    }
    implementation(platform(Libs.Jetbrains.kotlinBom))
    implementation(Libs.Jetbrains.kotlinStdLib)
    implementation(platform(Libs.Jetbrains.kotlinBom))
    testImplementation(Libs.JUnit.api)
    testRuntimeOnly(Libs.JUnit.engine)
}

tasks.test {
    useJUnitPlatform()
}
