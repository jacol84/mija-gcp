package pl.mija.validator

import kotlin.reflect.KProperty1


fun <T> validateManagerX(block: ValidManagerX<T>.() -> Unit): ValidManagerX<T> {

    val validManagerX = ValidManagerX<T>()
    validManagerX.let {
        it.block()
    }
    return validManagerX
}


class ValidManagerX<T>() {

    val blocks = mutableListOf<(T, key: String) -> ValidX?>()
    fun valid(key: String, model: T): List<ValidX> {
        return blocks.mapNotNull { it(model, key) }
    }

    fun <T> addValid(key: String, prop: KProperty1<T, Any?>, message: String, params: List<Any> = listOf()) = ValidX("$key.${prop.name}", message, params)

}

fun <T> ValidManagerX<T>.vRequire(prop: KProperty1<T, Any?>) =
    blocks.add { t: T, key: String -> prop.get(t).let { if (it == null) addValid(key, prop, "valid.required") else null } }

data class ValidX(val key: String, val message: String, val params: List<Any>)

