package co.rainbowreturns.models

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Company(val companyName: String, val stockTicker: String, val sector: String, val genderScore: Int, val soScore: Int, val raceScore: Int, val totalScore: Int)

class Companies {
  companion object {
      val fileName = "companies.csv"
      val inputStream = ClassLoader.getSystemResourceAsStream(fileName)
      val companies = inputStream?.bufferedReader()?.readLines()?.drop(1)?.map { line ->
          val columns = line.split(",")
          Company(
              companyName = columns[0].trim(),
              stockTicker = columns[1].trim(),
              sector = columns[2].trim(),
              genderScore = columns[3].trim().toInt(),
              soScore = columns[4].trim().toInt(),
              raceScore = columns[5].trim().toInt(),
              totalScore = columns[6].trim().toInt()
          )
      }
  }
}
