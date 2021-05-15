package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.mija.validator.*

internal class ValidKtSizeTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model("string")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsLowDataThenNotErrorList() {
        //given
        val model = Model("01234")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNullThenNotErrorList() {
        //given
        val model = Model<String>(null)
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsUpDataThenNotErrorList() {
        //given
        val model = Model("0123456789")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsUpExceedTheLengthDataThenNotErrorList() {
        //given
        val model = Model("01234567891")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    @Test
    fun testRequiredWhenIsDownExceedTheLengthDataThenNotErrorList() {
        //given
        val model = Model("0123")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).size(5, 10)
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
                assertEquals("valid.size", message)
                assertEquals(5, params[0])
                assertEquals(10, params[1])
                assertEquals(listOf<Any>(5, 10), params)
            }
        }
    }

}

