package co.rainbowreturns.models

import kotlinx.serialization.Serializable

@Serializable
data class Company(val id: String, val companyName: String, val stockTicker: String, val sector: String, val stockPrice: Int, val deiScore: Int, )
