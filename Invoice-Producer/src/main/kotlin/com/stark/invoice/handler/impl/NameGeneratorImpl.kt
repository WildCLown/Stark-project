package com.stark.invoice.handler.impl

import com.google.gson.Gson
import com.stark.invoice.handler.NameGenerator
import com.stark.invoice.model.RandomUserResponse
import java.net.HttpURLConnection
import java.net.URL

class NameGeneratorImpl: NameGenerator {
    override fun generateNames(count: Int): List<String> {
        val url = "https://randomuser.me/api/?inc=name&results=$count"
        val connection = URL(url).openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "GET"
            connection.inputStream.bufferedReader().use { reader ->
                val response = reader.readText()
                parseNames(response)
            }
        } finally {
            connection.disconnect()
        }
    }

    private fun parseNames(jsonResponse: String): List<String> {
        val randomUserResponse = gson.fromJson(jsonResponse, RandomUserResponse::class.java)

        return randomUserResponse.results.map {
            "${it.name.first} ${it.name.last}"
        }
    }

    private val gson = Gson()
}