package pl.mija.gcp.app.util.validator

import kotlin.reflect.KProperty1


fun <T> validateManager(s: String, t: T, block: ValidManager<T>.() -> Unit) {
    val validManager = ValidManager(s, t)
    validManager.let {
        it.block()
    }
}


class ValidManager<T>(val key: String, val t: T) {
    val list: List<Valid2> = mutableListOf()
    fun required(kProperty1: KProperty1<T, Long?>) {
        if (kProperty1.get(t) == null) {
            list.add(Valid2("$key.${kProperty1.name}", "valid.requires"))
        }
    }
}

data class Valid2(val key: String, val message: String, val params: List<Any> = listOf())
class Valid(val key: String, val field: String, val error: String)

object Valids {
    val REQUIRES = { x: Valid, obj: Any? -> if (obj != null) Valid(x.key, x.field, "Is not requires") }
    val POSITIVE = { x: Valid, obj: Long? -> if (obj != null && obj <= 0) Valid(x.key, x.field, "Is not positive") }
    val NotBlank = { x: Valid, obj: String? -> if (obj != null && obj.isBlank()) Valid(x.key, x.field, "Is not blank") }
}