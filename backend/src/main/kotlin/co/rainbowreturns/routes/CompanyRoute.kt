package co.rainbowreturns.routes

import co.rainbowreturns.models.Companies
import co.rainbowreturns.models.Company
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.companyRouting() {
    route("/companies") {
        get {
            call.respond(Companies.Companion.companies!!)
//            return Companies.Companion.companies
        }
        get("/{name?}") {
            val desired = Companies.Companion.companies?.filter { it.companyName == call.parameters["name"]  }
            if (desired?.size!! > 1) {
                call.respond(desired)
            }else if (desired.size == 1) {
                call.respond(desired[0])
            }else {
                call.respond(mapOf("error" to "no matching company found"))
            }
        }
    }
}