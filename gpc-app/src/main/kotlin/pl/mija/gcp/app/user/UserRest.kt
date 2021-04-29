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
        post("/user") {
            TODO("read post parameter")
            val create = UserController().create()
            call.respond(create)
        }
        get("/user/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val result = UserController().getById(id)
            call.respondText(Gson().toJson(result), contentType = ContentType.Application.Json)
        }
        put("/user/{id}") {
//            TODO()
            val result = UserController().getAll()
            call.respondText(Gson().toJson(result), contentType = ContentType.Application.Json)
        }
        delete("/user/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val delete = UserController().delete(id)
            call.respondText(Gson().toJson(delete), contentType = ContentType.Application.Json)
        }
    }
}

val mutableList = mutableListOf<User>()

class UserController {
    fun getAll(): List<User> {
        return mutableList
    }

    fun getById(id: Long): User? {
        return mutableList.find { it.id == id }
    }

    fun create(name: String = "Jacek", lastName: String = UUID.randomUUID().toString()): User {
        val element = User(id.incrementAndGet(), name, lastName)
        mutableList.add(element)
        return element
    }

    fun delete(id: Long): Boolean {
        return mutableList.removeIf { it.id == id }
    }


}