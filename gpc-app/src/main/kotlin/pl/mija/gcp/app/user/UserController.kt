package pl.mija.gcp.app.user

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import java.util.*
import java.util.concurrent.atomic.AtomicLong

object Id {
    val x = AtomicLong(0)
}

class UserController {

    val mutableList = mutableListOf<User>()

    fun api(): Route.() -> Unit = {
//        get("/user",ContentType.Application.Json) {
//
//        }
        get("/user") {
//            val id = call.parameters["id"]!!.toLong()
            Id.x.incrementAndGet()
            mutableList.add(User(Id.x.incrementAndGet(), "Jacek", UUID.randomUUID().toString()))
            call.respondText(Gson().toJson(mutableList), contentType = ContentType.Application.Json)
        }
    }

}
