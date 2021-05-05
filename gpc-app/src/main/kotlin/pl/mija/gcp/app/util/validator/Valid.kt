package pl.mija.gcp.app.util.validator



fun validateManager(s: String, block: ValidManager.() -> Unit) {
    val validManager = ValidManager(s)
    validManager.let {
        it.block()
    }
}


class ValidManager(val key: String) {
    fun required(s: String, id: Long?) {
        TODO("Not yet implemented")
    }
}

class Valid(val key: String, val field: String, val error: String)

object Valids {
    val REQUIRES = { x: Valid, obj: Any? -> if (obj != null) Valid(x.key, x.field, "Is not requires") }
    val POSITIVE = { x: Valid, obj: Long? -> if (obj != null && obj <= 0) Valid(x.key, x.field, "Is not positive") }
    val NotBlank = { x: Valid, obj: String? -> if (obj != null && obj.isBlank()) Valid(x.key, x.field, "Is not blank") }
}