package pl.mija.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ValidKtPositiveTest {

    @Test
    fun testPositiveWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model(5L)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Long>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testPositiveWhenIsZeroThenNotErrorList() {
        //given
        val model = Model(0L)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Long>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testPositiveWhenIsNullThenNotErrorList() {
        //given
        val model = Model<Long>(null)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Long>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testPositiveWhenIsNotCorrectlyThenHasErrorList() {
        //given
        val model = Model(-5L)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Long>::id)
        }
        //then
        myAssert(validate)
    }

    private fun myAssert(validate: ValidManager<Model<Long>>) {
        with(validate.list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.positive", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }

}