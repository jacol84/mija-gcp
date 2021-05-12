package pl.mija.validator

import kotlin.reflect.KProperty1


fun <T> validateManagerY(block: ValidManagerY<T>.() -> Unit): ValidManagerY<T> {
    val validManagerY = ValidManagerY<T>()
    validManagerY.block()
    return validManagerY
}


class ValidManagerY<T> {
    fun prop(prop: KProperty1<T, Any?>):ValidManagerY<T>.Prop<T> = Prop(prop)

    inner class Prop<U>(prop: KProperty1<T, Any?>) {
        fun isPositive() {


        }

    }


}


data class ValidY(val key: String, val message: String, val params: List<Any>)

