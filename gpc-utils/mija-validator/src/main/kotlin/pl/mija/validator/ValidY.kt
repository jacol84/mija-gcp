package pl.mija.validator

import kotlin.math.sign
import kotlin.reflect.KProperty1


fun <T> validateManagerY(block: ValidManagerY<T>.() -> Unit): ValidManagerY<T> {
    val validManagerY = ValidManagerY<T>()
    validManagerY.block()
    return validManagerY
}


class ValidManagerY<T> {
    private val blocks = mutableListOf<(T, key: String) -> ValidY?>()
    fun <U> prop(prop: KProperty1<T, U?>): ValidManagerY<T>.Prop<U> = Prop(prop)

    inner class Prop<U>(val prop: KProperty1<T, U?>) {
        fun add(block: ((T, key: String) -> ValidY?)) {
            blocks.add(block)
        }
    }

    fun valid(key: String, model: T): List<ValidY> {
        return blocks.mapNotNull { it(model, key) }
    }
}

fun <T, U : Number> ValidManagerY<T>.Prop<U>.positive() =
    add { t: T, key: String -> prop.get(t)?.let { if ((it is Long && it.sign < 0) || it.toDouble() < 0) addValid(key, prop, "valid.isNotPositive") else null } }

fun <T, U : Any> ValidManagerY<T>.Prop<U>.require() =
    add { t: T, key: String -> prop.get(t).let { if (it == null) addValid(key, prop, "valid.required") else null } }

fun <T> addValid(key: String, prop: KProperty1<T, Any?>, message: String, params: List<Any> = listOf()) = ValidY("$key.${prop.name}", message, params)

data class ValidY(val key: String, val message: String, val params: List<Any>)

