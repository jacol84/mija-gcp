package pl.mija.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ValidKtNotBlankTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model("string")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsNullDataThenNotErrorList() {
        //given
        val model = Model("string")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        assertEquals(0, validate.list.size)
    }

    @Test
    fun testRequiredWhenIsSpaceThenHasErrorList() {
        //given
        val model = Model(" ")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        myAssert(validate)
    }

    @Test
    fun testRequiredWhenIsTabThenHasErrorList() {
        //given
        val model = Model("\t")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        myAssert(validate)
    }

    @Test
    fun testRequiredWhenIsNewLineThenHasErrorList() {
        //given
        val model = Model("\n\r")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        myAssert(validate)
    }

    @Test
    fun testRequiredWhenIsMixThenHasErrorList() {
        //given
        val model = Model("   \n\r    ")
        //when
        val validate = validateManager("key", model) {
            notBlank(Model<String>::id)
        }
        //then
        myAssert(validate)
    }

    private fun myAssert(validate: ValidManager<Model<String>>) {
        with(validate.list) {
            assertEquals(1, size)
            with(get(0)) {
                assertEquals("key.id", key)
                assertEquals("valid.blank", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }


}

