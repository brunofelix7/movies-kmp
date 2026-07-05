package dev.brunofelix.data.api

import dev.brunofelix.data.api.dto.MovieListDto

interface KtorService {
    suspend fun getMovies(category: String): MovieListDto
}