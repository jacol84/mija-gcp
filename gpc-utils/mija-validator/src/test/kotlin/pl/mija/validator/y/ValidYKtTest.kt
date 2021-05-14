package pl.mija.validator.y

import org.junit.jupiter.api.Test
import pl.mija.validator.*
import pl.mija.validator.y.ModelYValid.valid
import kotlin.test.assertEquals

object ModelYValid {
    val valid =
        validateManagerY<ModelY> {
            prop(ModelY::id).vIsPositive()
            with(prop(ModelY::s)) {
                vIsRequire()
            }
        }
}


internal class ValidYKtTest {
    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(null, "string")
        //when
        val list = valid.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNotCorrectedDataThenErrorList() {
        //given
        val model = ModelY(-1L, null)
        //when
        val list = valid.valid("key", model)
        //then
        assertEquals(2, list.size)
        assertEquals("valid.isNotPositive", list[0].message)
        assertEquals("valid.required", list[1].message)
    }

    @Test
    fun testCustomValidWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(0L, "string")
        //and
        val valid = validateManagerY<ModelY> {
            prop(ModelY::id).vIsNotZero()
        }
        //when
        val list = valid.valid("key", model)
        //then
//        println(list[0].message)
        assertEquals(0, list.size)
    }
}

fun <T, Long> ValidManagerY<T>.Prop<Long>.vIsNotZero() =
    add { t: T, key: String -> prop.get(t).let { if (it != 0L) addValid(key, prop, "valid.my") else null } }

data class ModelY(val id: Long?, val s: String?)
