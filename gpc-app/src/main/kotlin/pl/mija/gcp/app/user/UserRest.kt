package pl.mija.gcp.app.user

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.concurrent.atomic.AtomicLong

val id = AtomicLong(0)


fun userAPI(): Route.() -> Unit = {
//    accept(ContentType.Application.Json) {
    get("/user") {
        val result = UserController().getAll()
        call.respond(HttpStatusCode.OK, result)
    }
    get("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val result = UserController().getById(id)
        if (result != null) call.respond(result) else call.respond(HttpStatusCode.NotFound, Gson().toJson("No found user by id=$id"))

    }
    post("/user") {
        val user = call.receive<UserCreate>()
        val create = UserController().create(user)
        call.respond(HttpStatusCode.Created, create)
    }
    put("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val user = call.receive<User>()
        if (user.id == id) {
            val result = UserController().put(id, user)
            call.respond(HttpStatusCode.Created, result)
        } else {
            call.respond(HttpStatusCode.NotFound, Gson().toJson("No found user by id=$id"))
        }
    }
    delete("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val delete = UserController().delete(id)
        call.respondText(Gson().toJson(delete), contentType = ContentType.Application.Json)
    }
//    }
}

val mutableList = mutableListOf<User>()

class UserController {
    fun getAll(): List<User> {
        return mutableList
    }

    fun getById(id: Long): User? {
        return mutableList.find { it.id == id }
    }

    fun create(user: UserCreate): User {
        val element = User(id.incrementAndGet(), user.name, user.lastName)
        mutableList.add(element)
        return element
    }

    fun put(id: Long, user: User): User {
        mutableList.removeIf { it.id == id }
        mutableList.add(user)
        return user
    }

    fun delete(id: Long): Boolean {
        return mutableList.removeIf { it.id == id }
    }

}