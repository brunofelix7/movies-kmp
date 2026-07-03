package dev.brunofelix.data.repository

import dev.brunofelix.data.api.KtorService
import dev.brunofelix.data.api.mapper.toDomain
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.repository.MovieRepository
import dev.brunofelix.domain.model.enums.CategoryType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getMovies(): List<MovieSection> {
        return withContext(dispatcher) {
            val popularMovies = async {
                api.getMovies(CategoryType.POPULAR.value).results?.map { it.toDomain() }
            }.await()
            val topRatedMovies = async {
                api.getMovies(CategoryType.TOP_RATED.value).results?.map { it.toDomain() }
            }.await()
            val upcomingMovies = async {
                api.getMovies(CategoryType.UPCOMING.value).results?.map { it.toDomain() }
            }.await()
            listOf(
                MovieSection(CategoryType.POPULAR, popularMovies ?: emptyList()),
                MovieSection(CategoryType.TOP_RATED, topRatedMovies ?: emptyList()),
                MovieSection(CategoryType.UPCOMING, upcomingMovies ?: emptyList())
            )
        }
    }
}