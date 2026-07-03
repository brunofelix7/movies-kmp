package dev.brunofelix.domain.repository

import dev.brunofelix.domain.model.MovieSection

interface MovieRepository {
    suspend fun getMovies(): List<MovieSection>
}