package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import pl.mija.validator.validateManagerY

internal class ValidYKtTest {
    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(null, "string")
        //when
        val valid = validateManagerY<ModelY> {
            prop(ModelY::id).isPositive()
            with(prop(ModelY::s)) {
                isPositive()
            }
        }
        //then
        assertNotNull(valid)
    }
}

data class ModelY(val id: Long?, val s: String)
