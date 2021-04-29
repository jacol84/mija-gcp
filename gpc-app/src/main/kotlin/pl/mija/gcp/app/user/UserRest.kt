package pl.mija.gcp.app.user

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*
import java.util.concurrent.atomic.AtomicLong

val id = AtomicLong(0)


fun userAPI(): Route.() -> Unit = {
    accept(ContentType.Application.Json) {
        get("/user") {
            val result = UserController().getAll()
            call.respondText(Gson().toJson(result), ContentType.Application.Json)
        }
        get("/user/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val result = UserController().getById(id)
            call.respondText(Gson().toJson(result), contentType = ContentType.Application.Json)
        }
        post("/user") {
            UserController().createRandom()
            call.respondText(Gson().toJson(true), contentType = ContentType.Application.Json)
        }
        put("/user") {
            val result = UserController().getAll()
            call.respondText(Gson().toJson(result), contentType = ContentType.Application.Json)
        }
        delete("/user") {
            val result = UserController().getAll()
            call.respondText(Gson().toJson(result), contentType = ContentType.Application.Json)
        }
    }
}

val mutableList = mutableListOf<User>()

class UserController {
    fun getAll(): List<User> {
        return mutableList
    }

    fun getById(id: Long): User? {
        return mutableList.find { it.id.equals(id) }
    }

    fun createRandom(): List<User> {
        mutableList.add(User(id.incrementAndGet(), "Jacek", UUID.randomUUID().toString()))
        return mutableList
    }


}
