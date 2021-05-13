package pl.mija.validator

import kotlin.math.sign
import kotlin.reflect.KProperty1


fun <T> validateManagerY(block: ValidManagerY<T>.() -> Unit): ValidManagerY<T> {
    val validManagerY = ValidManagerY<T>()
    validManagerY.block()
    return validManagerY
}


class ValidManagerY<T> {
    lateinit var list: List<ValidY>
    val blocks = mutableListOf<(T, key: String) -> ValidY?>()
    fun <U> prop(prop: KProperty1<T, U?>): ValidManagerY<T>.Prop<T, U> = Prop(prop)

    inner class Prop<T, U>(val prop: KProperty1<T, U?>) {

    }

    fun <U : Number> ValidManagerY<T>.Prop<T, U>.vIsPositive() {
        blocks.add { t: T, key: String -> prop.get(t).let { if (it == null) addValid(key, prop, "valid.required") else null } }
    }
    fun <T> addValid(key: String, prop: KProperty1<T, Any?>, message: String, params: List<Any> = listOf()) = ValidY("$key.${prop.name}", message, params)
}





data class ValidY(val key: String, val message: String, val params: List<Any>)

