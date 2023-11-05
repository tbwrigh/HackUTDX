package co.rainbowreturns.routes

import co.rainbowreturns.models.Companies
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.recommendationRouting() {
    route("/recommendation") {
        get("/top/{n?}") {
            var num = call.parameters["n"]?.toInt()
            if (num == null) {
                num = 5
            }
            val desired = Companies.Companion.companies?.sortedBy { -1*it.totalScore }?.take(num)

            call.respond(desired!!)
        }

        get("/top/{n?}/categories/{categories?}") {
            var num = call.parameters["n"]?.toInt()
            if (num == null) {
                num = 5
            }

            var categories = call.parameters["categories"]
            if (categories == null) {
                categories = "gender"
            }

            val categoryList = categories.split("+")

            val desired = Companies.Companion.companies?.sortedBy { company -> categoryList.sumOf {
                        category -> if (category == "gender") -1 * company.genderScore else (if (category == "rage") -1 * company.raceScore else -1 * company.soScore)
                    }
                }?.take(
                    num!!
                )

            call.respond(desired!!)
        }
    }
}