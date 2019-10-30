package com.adamratzman.explorer

import spark.ModelAndView
import spark.template.handlebars.HandlebarsTemplateEngine
import java.awt.Color
import kotlin.random.Random

fun getRandomColor(): String {
    val underlineColor = Color.getHSBColor(Random.nextFloat(), Random.nextFloat(), 0.8f)
    return String.format("#%02x%02x%02x", underlineColor.red, underlineColor.green, underlineColor.blue)
}

fun HandlebarsTemplateEngine.render(map: Map<String, Any?>, name: String) = render(ModelAndView(map, name))

data class Campaign(val name: String, val id: Int, val pages: List<Page>)
data class Page(val name: String, val pageId: Int)

class QubitModel(val qubit: Qubit)