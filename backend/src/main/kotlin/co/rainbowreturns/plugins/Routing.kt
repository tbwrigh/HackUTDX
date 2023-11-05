package co.rainbowreturns.plugins

import co.rainbowreturns.routes.companyRouting
import co.rainbowreturns.routes.recommendationRouting
import co.rainbowreturns.routes.stockRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        companyRouting()
        recommendationRouting()
        stockRouting()
    }
}
