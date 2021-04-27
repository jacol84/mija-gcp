package pl.mija.gcp.app

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.webjars.*
import io.ktor.websocket.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.css.*
import kotlinx.html.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.mija.gcp.app.user.UserController
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.mapOf
import kotlin.collections.set

val uuid = UUID.randomUUID()

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
class Application2{
    companion object {
        val logger by logger()
    }
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    runBlocking {
        cos()
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)

        allowCredentials = true
        anyHost()
        maxAgeInSeconds = Duration.ofDays(1).toSeconds()

        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    install(Locations) {
    }

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Webjars) {
        path = "/webjars" //defaults to /webjars
        zone = ZoneId.systemDefault() //defaults to ZoneId.systemDefault()
        zone = ZoneId.of("Europe/Warsaw"); //defaults to ZoneId.systemDefault()
    }

    install(io.ktor.websocket.WebSockets) {
        pingPeriodMillis = 15000L;
        timeoutMillis = 15000L;
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        it.UserController.userController()
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
        post("/"){
            call.respondText("POST HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-dsl") {
            val uuidRandom = UUID.randomUUID().toString()
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n Krzysiek " }
                        }
                    }
                    div {
                        span { +"TEA !!!!!!!!!!" }
                    }
                    div { +uuid.toString() }
                    div { +uuidRandom }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.red
                }
                p {
                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
        }

        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }

        get("/json/gson") {
            call.respond(
                mapOf(
                    "hello" to "world",
                    "date" to Date(),
                    "dateFormat" to DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(LocalDateTime.now()),
                    "time" to Date().time,
                    "uuid" to uuid,
                    "locale" to Locale.getDefault(),
                    "LocalDate" to LocalDate.now(),
                    "LocalTime" to LocalTime.now(),
                    "LocalDateTime" to LocalDateTime.now(),
                )
            )
        }

        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }

        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }

        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }

        webSocket("/myws/echo") {
            send(Frame.Text("Hi from server"))
            while (true) {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    send(Frame.Text("Client said: " + frame.readText()))
                }
            }
        }
    }

}



fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(this::class.java.name) }
}

private suspend fun cos() {
//    launch {
//        delay(200L)
//        println("Task from runBlocking")
//    }

    coroutineScope { // Creates a new coroutine scope
        launch {
            delay(500L)
            Application2.logger.info("Task from nested launch")
        }// Creates a new coroutine scope
        launch {
            delay(500L)
            Application2.logger.info("Task from nested launch 2")
        }// Creates a new coroutine scope
        launch {
            delay(500L)
            Application2.logger.info("Task from nested launch 3")
        }

        delay(100L)
        Application2.logger.info("Task from coroutine scope") // This line will be printed before nested launch
    }

    Application2.logger.info("Coroutine scope is over") // This
}

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}")
data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

data class MySession(val count: Int = 0)

fun FlowOrMetaDataContent.styleCss(builder: CSSBuilder.() -> Unit) {
    style(type = ContentType.Text.CSS.toString()) {
        +CSSBuilder().apply(builder).toString()
    }
}

fun CommonAttributeGroupFacade.style(builder: CSSBuilder.() -> Unit) {
    this.style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
