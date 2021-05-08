package pl.mija.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ValidKtSizeTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model("string")
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsLowDataThenNotErrorList() {
        //given
        val model = Model("01234")
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsNullThenNotErrorList() {
        //given
        val model = Model<String>(null)
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsUpDataThenNotErrorList() {
        //given
        val model = Model("0123456789")
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsUpExceedTheLengthDataThenNotErrorList() {
        //given
        val model = Model("01234567891")
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        myAssert(validate)
    }

    @Test
    fun testRequiredWhenIsDownExceedTheLengthDataThenNotErrorList() {
        //given
        val model = Model("0123")
        //when
        val validate = validateManager("key", model) {
            size(Model<String>::id, 5, 10)
        }
        //then
        myAssert(validate)
    }

    private fun myAssert(validate: ValidManager<Model<String>>) {
        with(validate.list) {
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

