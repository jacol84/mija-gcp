package pl.mija.gcp.app.user


import pl.mija.validator.Valid2
import pl.mija.validator.validateManager
import java.util.*

@JvmInline
inline value class Password(private val s: String){
    fun xxx(): String {
        return s
    }
}


internal data class UserCreate(
    val name: String?,
    val lastName: String?,
    val password: Password
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

fun UserEdit.validateModel(): List<Valid2> {
    val userCreate = UserCreate("na", "z", Password("aaa"))
    val validateManager = validateManager("UserEdit", this) {
        UserEdit::id.also {
            required(it)
            positive(it)
        }
        UserEdit::name.also {
            required(it)
            notBlank(it)
            size(it, 2, 40)
        }
        required(UserEdit::lastName)
        notBlank(UserEdit::lastName)
    }
    return validateManager.list
}


data class User(
    val id: Long,
    val name: String,
    val lastName: String
)