package co.rainbowreturns.utils

import java.io.InputStream
import java.util.*

class PropertiesLoader {
    companion object {
        var properties: Properties? = null
        var loaded = false
        fun load() {
            if (loaded) {
                return
            }
            val fileName = "config.properties"
            val classLoader = this::class.java.classLoader
            val inputStream = classLoader.getResourceAsStream(fileName)
                ?: throw IllegalArgumentException("Properties file '$fileName' not found.")
            properties = Properties()
            properties?.load(inputStream)
            println("load")
            println(properties)
            loaded = true
        }
    }
}