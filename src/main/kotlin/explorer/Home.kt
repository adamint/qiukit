package explorer

import explorer.QuantumExplorer
import explorer.getMap
import explorer.handlebars
import explorer.render

fun QuantumExplorer.home() {
    spark.Spark.get("/") { _, _ ->
        val map = getMap("Home", "home", false)

        map["campaigns"] = campaigns

        handlebars.render(map, "index.hbs")
    }
}