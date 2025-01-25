package com.stark.invoice.handler.impl

import com.google.gson.Gson
import com.stark.invoice.handler.NameGenerator
import com.stark.invoice.model.RandomUserResponse
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class NameGeneratorImpl: NameGenerator {
    override fun generateNames(count: Int): List<String> {
        val url = "https://randomuser.me/api/?inc=name&results=$count"
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()

        return try {
            val response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            )
            parseNames(response.body())
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun parseNames(jsonResponse: String): List<String> {
        val randomUserResponse = gson.fromJson(
            jsonResponse,
            RandomUserResponse::class.java
        )

        return randomUserResponse.results.map {
            "${it.name.first} ${it.name.last}"
        }
    }

    private val gson = Gson()
}