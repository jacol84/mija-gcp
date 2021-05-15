package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.mija.validator.Model
import pl.mija.validator.ValidY
import pl.mija.validator.positive
import pl.mija.validator.validateManagerY

internal class ValidKtPositiveTest {

    @Test
    fun testPositiveWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model(5L)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testPositiveWhenIsZeroThenNotErrorList() {
        //given
        val model = Model(0L)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testPositiveWhenIsDoubleThenNotErrorList() {
        //given
        val model = Model(0.1)
        //and
        val validate = validateManagerY<Model<Double>> {
            prop(Model<Double>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testPositiveWhenIsFloatThenNotErrorList() {
        //given
        val model = Model(0.1f)
        //and
        val validate = validateManagerY<Model<Float>> {
            prop(Model<Float>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testPositiveWhenIsNullThenNotErrorList() {
        //given
        val model = Model<Long>(null)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testPositiveWhenIsNotCorrectlyThenHasErrorList() {
        //given
        val model = Model(-5L)
        //and
        val validate = validateManagerY<Model<Long>> {
            prop(Model<Long>::id).positive()
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    @Test
    fun testPositiveWhenIsMinusZeroThenHasErrorList() {
        //given
        val model = Model(-0.0000000000000000001)
        //and
        val validate = validateManagerY<Model<Double>> {
            prop(Model<Double>::id).positive()
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