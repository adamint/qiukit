package explorer

import spark.Spark.get
import spark.Spark.path

fun QuantumExplorer.campaigns() {
    path("/campaigns") {
        get("") { _, response -> response.redirect("/campaigns/") }

        get("/") { _, _ ->
            val map = getMap("Campaigns", "campaigns", true)

            map["campaigns"] = campaigns

            handlebars.render(map, "campaigns-home.hbs")
        }

        campaigns.forEach { campaign ->
            path("/${campaign.id}") {
                get("") {_, _ -> TODO() }
                campaign.pages.forEach { page ->
                    get("/${page.pageId}") { _, _ ->
                        val map = getMap("${page.name} | ${campaign.name} | Campaigns","",false)
                        handlebars.render(map, "${campaign.id}-${page.pageId}.hbs")
                    }
                }
            }
        }
    }
}