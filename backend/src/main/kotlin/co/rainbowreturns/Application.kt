package co.rainbowreturns

import co.rainbowreturns.models.Companies
import com.auth0.jwk.JwkProviderBuilder
import co.rainbowreturns.plugins.*
import co.rainbowreturns.routes.companyRouting
import co.rainbowreturns.utils.PropertiesLoader
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*;
import io.ktor.server.plugins.cors.routing.*;
import io.ktor.server.routing.*
import java.util.concurrent.TimeUnit

fun main() {
    PropertiesLoader.Companion.load()
    embeddedServer(Netty, port = 5174, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun validateCreds(credential: JWTCredential): JWTPrincipal? {
    if (PropertiesLoader.Companion.properties == null) {
        PropertiesLoader.Companion.load()
    }
    var audience = PropertiesLoader.Companion.properties?.getProperty("AUDIENCE")

    val containsAudience = credential.payload.audience.contains(audience)

    if (containsAudience) {
        return JWTPrincipal(credential.payload)
    }

    return null
}


fun Application.module() {
    if (PropertiesLoader.Companion.properties == null) {
        PropertiesLoader.Companion.load()
    }
    var issuer = PropertiesLoader.Companion.properties?.getProperty("ISSUER")
    val jwkProvider = JwkProviderBuilder(issuer!!)
        .cached(10, 24, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt("auth0") {
            verifier(jwkProvider, issuer)
            validate { credential -> validateCreds(credential) }
        }
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

    routing {
        companyRouting()
    }
}
