package com.adamratzman.explorer

import spark.Spark.get
import spark.Spark.path

fun QuantumExplorer.campaigns() {
    path("/campaigns") {
        get("") { _, response -> response.redirect("/campaigns/") }

        get("/") { _, _ ->
            val map = getMap("Campaigns", "campaigns", false)

            map["campaigns"] = campaigns

            handlebars.render(map, "campaigns-home.hbs")
        }
    }
}