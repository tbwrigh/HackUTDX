package co.rainbowreturns

import co.rainbowreturns.plugins.*
import co.rainbowreturns.utils.PropertiesLoader
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*;
import io.ktor.server.plugins.cors.routing.*;

fun main() {
    PropertiesLoader.Companion.load()
    embeddedServer(Netty, port = 5174, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


fun Application.module() {
    if (PropertiesLoader.Companion.properties == null) {
        PropertiesLoader.Companion.load()
    }

    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowHeader("authorization")
        allowCredentials = true
        allowNonSimpleContentTypes = true

    }

    configureSerialization()
    configureRouting()
}
