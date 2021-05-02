package pl.mija.gcp.app.user

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.mija.gcp.app.controller.MessageValid
import java.util.concurrent.atomic.AtomicLong

val id = AtomicLong(0)

fun Route.userApi() {
//    accept(ContentType.Application.Json) {
    get("/user") {
        val result = UsersController().getAll()
        call.respond(HttpStatusCode.OK, result)
    }
    get("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val result = UsersController().getById(id)
        if (result != null) call.respond(result) else call.respond(HttpStatusCode.NotFound, MessageValid("No found user by id=$id"))

    }
    post("/user") {
        val user = call.receive<UserCreate>()
        val create = UsersController().create(user)
        call.respond(HttpStatusCode.Created, create)
    }
    put("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val user = call.receive<User>()
        if (user.id == id) {
            val result = UsersController().put(id, user)
            call.respond(HttpStatusCode.Created, result)
        } else {
            call.respond(HttpStatusCode.NotFound, MessageValid("No found user by id=$id"))
        }
    }
    delete("/user/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val delete = UsersController().delete(id)
        call.respond(HttpStatusCode.OK, delete)
    }
    //}
}
