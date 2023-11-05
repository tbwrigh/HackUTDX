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
import java.util.Random

@Serializable
data class AlphaVantageResponse(val `Time Series (Daily)`: JsonObject)

@Serializable
data class AlphaVantageDayResponse(val open: Int, val high: Int, val low: Int, val close: Int, val volume: Int)

suspend fun fetchCurrentPrice(symbol: String): Double {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    val apiKey = PropertiesLoader.Companion.properties?.get("STOCK_API_KEY").toString()

    val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=$symbol&apikey=$apiKey"
    try {
        val response: AlphaVantageResponse = client.get(url).body()

        client.close()

        val most_recent = response.`Time Series (Daily)`.keys.sortedBy { date ->
            val parts = date.split("-")
            parts[0].toInt()*10000 + parts[1].toInt() * 100 + parts[2].toInt()
        }.max()

        val day = response.`Time Series (Daily)`["1. open"]


        return day.toString().toDouble()
    }catch (e: Exception) {
        // due to cost restrictions, we aren't paying for the api and there is very limited calls to it
        val rand = Random()
        return rand.nextDouble() * (175 - 30) + 30
    }
}

