package co.rainbowreturns.models

import co.rainbowreturns.utils.PropertiesLoader
import io.ktor.client.*
import io.ktor.client.call.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

@Serializable
data class AlphaVantageResponse(val `Time Series (Daily)`: JsonObject)

suspend fun fetchCurrentPrice(symbol: String): JsonObject? {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    val apiKey = PropertiesLoader.Companion.properties?.get("STOCK_API_KEY")
    val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=$symbol&apikey=$apiKey"
    try {
        val response: AlphaVantageResponse = client.get(url).body()

        client.close()

        return response.`Time Series (Daily)`
    }catch (e: Exception) {
        return null
    }
}

