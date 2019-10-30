package com.adamratzman.explorer

fun QuantumExplorer.home() {
    spark.Spark.get("/") { _, _ ->
        val map = getMap("Home", "home", false)

        map["campaigns"] = campaigns

        handlebars.render(map, "index.hbs")
    }
}