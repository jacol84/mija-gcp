package pl.mija.validator.y

import org.junit.jupiter.api.Test
import pl.mija.validator.*
import kotlin.test.assertEquals


internal class ValidKtMoreValueTest {

    data class ModelY(val id: Long?, val s: String?)

    object ModelYValid {
        val valid =
            validateManagerY<ModelY> {
                prop(ModelY::id).positive()
                with(prop(ModelY::s)) {
                    require()
                }
            }
    }

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(null, "string")
        //when
        val list = ModelYValid.valid.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNotCorrectedDataThenErrorList() {
        //given
        val model = ModelY(-1L, null)
        //when
        val list = ModelYValid.valid.valid("key", model)
        //then
        assertEquals(2, list.size)
        assertEquals("valid.isNotPositive", list[0].message)
        assertEquals("valid.required", list[1].message)
    }

}


