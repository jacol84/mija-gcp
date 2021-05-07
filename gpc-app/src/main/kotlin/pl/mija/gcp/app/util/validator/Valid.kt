package pl.mija.gcp.app.util.validator

import kotlin.reflect.KProperty1


fun <T> validateManager(s: String, t: T, block: ValidManager<T>.() -> Unit): ValidManager<T> {
    val validManager = ValidManager(s, t)
    validManager.let {
        it.block()
    }
    return validManager
}


class ValidManager<T>(private val key: String,private val t: T) {
    val list: MutableList<Valid2> = mutableListOf()
    fun required(kProperty1: KProperty1<T, Any?>) {
        if (kProperty1.get(t) == null) {
            addValid(kProperty1, "valid.requires")
        }
    }

    fun positive(kProperty1: KProperty1<T, Long?>) {
        kProperty1.get(t)?.let {
            if (it < 0) {
                addValid(kProperty1, "valid.positive")
            }
        }
    }

    fun notBlank(kProperty1: KProperty1<T, String?>) {
        kProperty1.get(t)?.let {
            if (it.isBlank()) {
                addValid(kProperty1, "valid.blank")
            }
        }
    }

    private fun addValid(kProperty1: KProperty1<T, Any?>, message: String) {
        list.add(Valid2("$key.${kProperty1.name}", message))
    }
}

data class Valid2(val key: String, val message: String, val params: List<Any> = listOf())
class Valid(val key: String, val field: String, val error: String)

object Valids {
    val REQUIRES = { x: Valid, obj: Any? -> if (obj != null) Valid(x.key, x.field, "Is not requires") }
    val POSITIVE = { x: Valid, obj: Long? -> if (obj != null && obj <= 0) Valid(x.key, x.field, "Is not positive") }
    val NotBlank = { x: Valid, obj: String? -> if (obj != null && obj.isBlank()) Valid(x.key, x.field, "Is not blank") }
}