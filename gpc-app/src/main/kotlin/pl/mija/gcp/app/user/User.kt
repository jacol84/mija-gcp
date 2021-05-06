package pl.mija.gcp.app.user

import pl.mija.gcp.app.util.validator.Valid
import pl.mija.gcp.app.util.validator.validateManager
import java.util.*

internal data class UserCreate(
    val name: String?,
    val lastName: String?
) {
    fun mapper() = User(id.incrementAndGet(), name ?: "Name" + UUID.randomUUID(), lastName ?: "LastName" + UUID.randomUUID())
}


data class UserEdit(
    val id: Long?,
    val name: String?,
    val lastName: String?
) {
    fun mapper() = User(id!!, name ?: "Name" + UUID.randomUUID(), lastName ?: "LastName" + UUID.randomUUID())
}

fun UserEdit.validateModel(): List<Valid> {
    val z = this
    val kProperty1 = UserEdit::id
    val name1 = kProperty1.name
    val value = kProperty1.get(z)
    println(name1)
    println(value)

    validateManager("UserEdit",this) {
        kProperty1.name
        kProperty1.get(z)
        required(  UserEdit::id )
        positive("id", id)
        key("name") {
            notBlanck(id)
            notNull(id)
        }
    }
    return emptyList()
}


data class User(
    val id: Long,
    val name: String,
    val lastName: String
)