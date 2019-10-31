package com.adamratzman.explorer

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import spark.Spark
import spark.Spark.port
import spark.staticfiles.StaticFilesConfiguration
import spark.template.handlebars.HandlebarsTemplateEngine

val handlebars = HandlebarsTemplateEngine()

fun main() {
    port(80)
    val staticFileHandler = StaticFilesConfiguration()
    staticFileHandler.configure("/public")

    Spark.before("/*") { request, response ->
        staticFileHandler.consume(request.raw(), response.raw())
    }

    Spark.exception(Exception::class.java) { exception, _, _ ->
        exception.printStackTrace()
    }

    Spark.notFound { _, _ ->
        val map = getMap("404 - Not Found", "404", false)
        map["color"] = getRandomColor()
        handlebars.render(map, "404.hbs")
    }
    registerHelpers()

    QuantumExplorer()
}

class QuantumExplorer {
    val campaigns = listOf(
            Campaign("Classical Bits", 1, listOf(
                    Page("What's a bit?", 1),
                    Page("Representing a bit", 2),
                    Page("What's a gate?", 3),
                    Page("Examples of Gates", 4),
                    Page("NOT Gate", 5),
                    Page("Identity Gate", 6)
            )),
            Campaign("Introduction to Quantum Computing", 2, listOf(
                    Page("What is a qubit?", 1),
                    Page("How do we represent qubits?", 2),
                    Page("What happens when you read a qubit?", 3),
                    Page("Understanding qubit probabilities", 4),
                    Page("What exactly is Circle Notation?", 5),
                    Page("What's a relative and global phase?", 6)
            )),
            Campaign("Basic Gates", 3, listOf(
                    Page("What's a gate?", 0),
                    Page("Not Gate", 1),
                    Page("Read Gate", 2),
                    Page("Had Gate", 3)
            )),
            Campaign("Two Qubits", 4, listOf(
                    Page("Swap Gate", 1)
            ))
    )

    init {
        home()
        campaigns()
        iqe()
    }

}


private fun registerHelpers() {
    val field = handlebars::class.java.getDeclaredField("handlebars")
    field.isAccessible = true
    val handle = field.get(handlebars) as Handlebars

    handle.registerHelper("ifeq") { first: Any?, options: Options ->
        if (options.params[0].toString().equals(first?.toString(), true)) {
            options.fn()
        } else options.inverse()
    }
}

internal fun getMap(pageTitle: String, pageId: String, positionBottom: Boolean): MutableMap<String, Any?> {
    val map = mutableMapOf<String, Any?>()
    map["title"] = "Quantum Explorer | $pageTitle"
    map["page"] = pageId
    map["position-bottom"] = positionBottom
    map["color"] = getRandomColor()

    // meta
    map["description"] =
            "Interactive Quantum Explorer"

    return map
}
