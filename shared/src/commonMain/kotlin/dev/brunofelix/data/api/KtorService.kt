package dev.brunofelix.data.api

import dev.brunofelix.data.api.dto.MovieListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorService(
    private val client: HttpClient
) {
    suspend fun getMovies(category: String): MovieListDto {
        return client.get("movie/$category") {
            parameter("language", "en-US")
        }.body()
    }
}