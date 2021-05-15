package pl.mija.validator.y

import org.junit.jupiter.api.Test
import pl.mija.validator.*
import kotlin.test.assertEquals


internal class ValidKtCustomTest {

    data class ModelY(val id: Long?, val s: String?)

    private fun <T, Long> ValidManagerY<T>.Prop<Long>.onlyZero() =
        add { t: T, key: String ->
            prop.get(t).let {
                if (it != 0L) createValid(key, prop, "valid.my") else null
            }
        }

    @Test
    fun testCustomValidWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(0L, "string")
        //and
        val valid = validateManagerY<ModelY> {
            prop(ModelY::id).onlyZero()
        }
        //when
        val list = valid.valid("key", model)
        //then
//        println(list[0].message)
        assertEquals(0, list.size)
    }

    @Test
    fun testCustomValidWhenIsNotCorrectedDataThenErrorList() {
        //given
        val model = ModelY(1L, "string")
        //and
        val valid = validateManagerY<ModelY> {
            prop(ModelY::id).onlyZero()
        }
        //when
        val list = valid.valid("key", model)
        //then
        with(list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.my", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }


}


