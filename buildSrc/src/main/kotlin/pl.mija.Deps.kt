object Plugin {
    const val kotlinVersion = "kotlin-1.2.41" //??
}

object Libs {
    object Logback {
        const val classic = "ch.qos.logback:logback-classic:1.2.3"
    }

    object Apache {
        const val commonsText = "org.apache.commons:commons-text:1.9"
    }

    object Jetbrains {
        const val kotlinCss = "org.jetbrains:kotlin-css-jvm:1.0.0-pre.31-kotlin-1.2.41"
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    }



    object JUnit {
        const val version = "5.6.2"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
        const val api = "org.junit.jupiter:junit-jupiter-api:$version"
    }

    object Ktor {
        private const val version = "1.5.3"

        const val core = "io.ktor:ktor-server-core:$version"
        const val webjars = "io.ktor:ktor-webjars:$version"
        const val serverNetty = "io.ktor:ktor-server-netty:$version"
        const val websockets = "io.ktor:ktor-websockets:$version"
        const val html = "io.ktor:ktor-html-builder:$version"
        const val gson = "io.ktor:ktor-gson:$version"
        const val location = "io.ktor:ktor-locations:$version"

//        const val clientJs = "io.ktor:ktor-client-js:$version"
//        const val clientJvm = "io.ktor:ktor-client-core-jvm:$version"
//        const val clientApache = "io.ktor:ktor-client-apache:$version"
//        const val clientCIO = "io.ktor:ktor-client-cio:$version"
//        const val clientJsonJs = "io.ktor:ktor-client-json-js:$version"
//        const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
//        const val clientSerializationJs = "io.ktor:ktor-client-serialization-js:$version"
//        const val serverTestHost = "io.ktor:ktor-server-test-host:$version"
//        const val jackson = "io.ktor:ktor-jackson:$version"
    }
}