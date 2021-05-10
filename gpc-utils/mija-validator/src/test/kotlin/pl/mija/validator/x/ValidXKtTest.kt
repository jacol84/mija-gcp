package pl.mija.validator.x

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.mija.validator.ValidX
import pl.mija.validator.vRequire
import pl.mija.validator.validateManagerX

internal class ValidXKtTest {

    data class ModelX(var id: Long?, var name: String?)

    val validate = validateManagerX<ModelX> {
        vRequire(ModelX::id)
    }

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelX(null, "string")
        //when
        //and
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    private fun myAssert(list: List<ValidX>) {
        with(list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.required", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }
}


