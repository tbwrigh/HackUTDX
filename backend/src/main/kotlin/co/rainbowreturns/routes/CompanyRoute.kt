package co.rainbowreturns.routes

import co.rainbowreturns.models.Companies
import co.rainbowreturns.models.Company
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.util.*

fun Route.companyRouting() {
    route("/companies") {
        get {
            call.respond(Companies.Companion.companies!!)
//            return Companies.Companion.companies
        }
        get("/name/{name?}") {
            val desired = Companies.Companion.companies?.filter { it.companyName == call.parameters["name"]  }
            if (desired?.size!! > 1) {
                call.respond(desired)
            }else if (desired.size == 1) {
                call.respond(desired[0])
            }else {
                call.respond(mapOf("error" to "no matching company found"))
            }
        }
        get("/sector/{sector?}") {
            var sector = call.parameters["sector"]
            sector = sector?.lowercase()?.split(" ")?.joinToString(separator = " ") {
                it.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
            }
            if (Companies.Companion.sectors == null || !Companies.Companion.sectors.contains(sector)) {
                call.respond(mapOf("error" to "sector name not found"))
            }
            val desired = Companies.Companion.companies?.filter { it.sector == sector }
            call.respond(desired!!)
        }
    }

    route("/sectors") {
        get {
            call.respond(Companies.Companion.sectors!!)
        }
    }
}