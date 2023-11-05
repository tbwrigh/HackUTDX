package co.rainbowreturns.routes

import co.rainbowreturns.models.Companies
import co.rainbowreturns.models.fetchCurrentPrice
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.stockRouting() {
    route("/stock") {
        get("/price/{ticker?}") {
            val ticker = call.parameters["ticker"]
            if (ticker == null) {
                call.respond(mapOf("error" to "no ticker specified"))
            }
            val data = fetchCurrentPrice(ticker!!)
            if (data == null) {
                call.respond(mapOf("error" to "invalid ticker"))
            }
            call.respond(data!!)
        }

    }
}