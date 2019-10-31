package explorer

import com.adamratzman.qiukit.Qubit
import com.adamratzman.qiukit.QubitAmplitude.delta
import com.adamratzman.qiukit.utils.MathUtils
import spark.ModelAndView
import spark.template.handlebars.HandlebarsTemplateEngine
import java.awt.Color
import kotlin.math.pow
import kotlin.math.round
import kotlin.random.Random

fun getRandomColor(): String {
    val underlineColor = Color.getHSBColor(Random.nextFloat(), Random.nextFloat(), 0.8f)
    return String.format("#%02x%02x%02x", underlineColor.red, underlineColor.green, underlineColor.blue)
}

fun HandlebarsTemplateEngine.render(map: Map<String, Any?>, name: String) = render(ModelAndView(map, name))

data class Campaign(val name: String, val id: Int, val pages: List<Page>)
data class Page(val name: String, val pageId: Int)

class QubitModel(val id: Int, val qubit: Qubit, val zero: AmplitudeModel, val one: AmplitudeModel, var gate: String? = null,
                 var first: Boolean = false)

class AmplitudeModel(val sqrtProbability: Double, val phase: Double, val real: Double, val imaginary: Double,
                     val hasReal: Boolean = !MathUtils.equals(real, 0.0, delta),
                     val hasImaginary: Boolean = !MathUtils.equals(imaginary, 0.0, delta),
                     val probability: Double = sqrtProbability.pow(2.0).round(3))

fun Qubit.toModel(id: Int) = QubitModel(id, this,
        AmplitudeModel(zero.sqrtProbability, zero.angle,
                zero.complex.real.round(3), zero.complex.imaginary.round(3)),
        AmplitudeModel(one.sqrtProbability, one.angle, one.complex.real.round(3), one.complex.imaginary.round(3)))

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}