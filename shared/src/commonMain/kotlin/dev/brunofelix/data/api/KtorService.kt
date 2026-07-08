package dev.brunofelix.data.api

import dev.brunofelix.data.api.dto.MovieListDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface KtorService {
    suspend fun getMovies(category: String): MovieListDto
}

class KtorServiceImpl(
    private val client: KtorClient
) : KtorService {

    override suspend fun getMovies(category: String): MovieListDto {
        return client.httpClient.get("movie/$category") {
            parameter("language", "en-US")
        }.body()
    }
}