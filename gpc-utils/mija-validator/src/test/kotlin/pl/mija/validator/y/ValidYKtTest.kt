package pl.mija.validator.y

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import pl.mija.validator.ValidManagerY
import pl.mija.validator.vIsPositive
import pl.mija.validator.validateManagerY

internal class ValidYKtTest {
    @Test
    fun testRequiredWhenIsCorrectedDataThenNotErrorList() {
        //given
        val model = ModelY(null, "string")
        //when
        val valid = validateManagerY<ModelY> {
            prop(ModelY::id).vIsPositive()
            with(prop(ModelY::s)) {
//                vIsRe()
            }
        }
        //then
        assertNotNull(valid)
    }
}


data class ModelY(val id: Long?, val s: String)
