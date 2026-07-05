package dev.brunofelix.domain.repository

import dev.brunofelix.domain.util.Resource
import dev.brunofelix.domain.model.MovieSection

interface MovieRepository {
    suspend fun getMovies(): Resource<List<MovieSection>>
}