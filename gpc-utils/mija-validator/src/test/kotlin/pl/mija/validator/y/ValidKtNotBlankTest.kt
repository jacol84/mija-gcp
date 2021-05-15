package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.mija.validator.Model
import pl.mija.validator.ValidY
import pl.mija.validator.notBlank
import pl.mija.validator.validateManagerY

internal class ValidKtNotBlankTest {

    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = Model("string")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsNullDataThenNotErrorList() {
        //given
        val model = Model<String>(null)
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
        }
        //when
        val list = validate.valid("key", model)
        //then
        assertEquals(0, list.size)
    }

    @Test
    fun testRequiredWhenIsSpaceThenHasErrorList() {
        //given
        val model = Model(" ")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    @Test
    fun testRequiredWhenIsTabThenHasErrorList() {
        //given
        val model = Model("\t")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    @Test
    fun testRequiredWhenIsNewLineThenHasErrorList() {
        //given
        val model = Model("\n\r")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
        }
        //when
        val list = validate.valid("key", model)
        //then
        myAssert(list)
    }

    @Test
    fun testRequiredWhenIsMixThenHasErrorList() {
        //given
        val model = Model("   \n\r    ")
        //and
        val validate = validateManagerY<Model<String>> {
            prop(Model<String>::id).notBlank()
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
                assertEquals("valid.blank", message)
                assertEquals(emptyList<Any>(), params)
            }
        }
    }


}

