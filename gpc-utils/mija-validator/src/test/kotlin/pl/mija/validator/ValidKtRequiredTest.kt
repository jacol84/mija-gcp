package pl.mija.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ValidKtRequiredTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model(5L)
        //when
        val validate = validateManager("key", model) {
            required(Model::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }
    @Test
    fun testRequiredWhenIsNullThenNotErrorList() {
        //given
        val model = Model(-5L)
        //when
        val validate = validateManager("key", model) {
            required(Model::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsNotCorrectlyThenNotErrorList() {
        //given
        val model = Model(null)
        //when
        val validate = validateManager("key", model) {
            required(Model::id)
        }
        //then
        with(validate.list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.required", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }




}

