package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.mija.validator.*

internal class ValidKtRequiredTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model(5L)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).require()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNullThenNotErrorList() {
        //given
        val model = Model(-5L)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).require()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNotCorrectlyThenHasErrorList() {
        //given
        val model = Model<Long>(null)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).require()
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    private fun myAssert(list: List<ValidY>) {
        with(list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.positive", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }

}

