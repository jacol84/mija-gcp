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
    fun testPositiveWhenIsDoubleThenNotErrorList() {
        //given
        val model = Model(0.1)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Double>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testPositiveWhenIsFloatThenNotErrorList() {
        //given
        val model = Model(0.1f)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Float>::id)
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

    @Test
    fun testPositiveWhenIsMinusZeroThenHasErrorList() {
        //given
        val model = Model(-0.0000000000000000001)
        //when
        val validate = validateManager("key", model) {
            positive(Model<Double>::id)
        }
        //then
        myAssert(validate)
    }

    private fun <U> myAssert(validate: ValidManager<Model<U>>) {
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