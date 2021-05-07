package pl.mija.gcp.app.util.validator

import kotlin.reflect.KProperty1


fun <T> validateManager(s: String, t: T, block: ValidManager<T>.() -> Unit): ValidManager<T> {
    val validManager = ValidManager(s, t)
    validManager.let {
        it.block()
    }
    return validManager
}


class ValidManager<T>(private val key: String, private val t: T) {
    val list: MutableList<Valid2> = mutableListOf()

    fun required(prop: KProperty1<T, Any?>) = prop.get(t) ?: addValid(prop, "valid.requires")

    fun positive(prop: KProperty1<T, Long?>) = prop.get(t)?.takeIf { it < 0 }?.let { addValid(prop, "valid.positive") }

    fun notBlank(prop: KProperty1<T, String?>) = prop.get(t)?.takeIf { it.isBlank() }?.let { addValid(prop, "valid.blank") }

    fun size(prop: KProperty1<T, String?>, min: Int, max: Int) = prop.get(t)?.takeIf { it.length in min..max }?.let { addValid(prop, "valid.positive", listOf(min, max)) }

    private fun addValid(prop: KProperty1<T, Any?>, message: String, params: List<Any> = listOf()) = list.add(Valid2("$key.${prop.name}", message, params))
}

data class Valid2(val key: String, val message: String, val params: List<Any>)
class Valid(val key: String, val field: String, val error: String)

object Valids {
    val REQUIRES = { x: Valid, obj: Any? -> if (obj != null) Valid(x.key, x.field, "Is not requires") }
    val POSITIVE = { x: Valid, obj: Long? -> if (obj != null && obj <= 0) Valid(x.key, x.field, "Is not positive") }
    val NotBlank = { x: Valid, obj: String? -> if (obj != null && obj.isBlank()) Valid(x.key, x.field, "Is not blank") }
}