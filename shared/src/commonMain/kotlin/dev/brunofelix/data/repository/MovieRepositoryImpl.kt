package dev.brunofelix.data.repository

import dev.brunofelix.data.api.KtorService
import dev.brunofelix.data.api.mapper.toDomain
import dev.brunofelix.domain.util.Resource
import dev.brunofelix.domain.util.toResource
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.model.enums.CategoryType
import dev.brunofelix.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: KtorService,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getMovies(): Resource<List<MovieSection>> {
        return withContext(dispatcher) {
            runCatching {
                val popularDeferred = async {
                    api.getMovies(CategoryType.POPULAR.value).results?.map {
                        it.toDomain()
                    } ?: emptyList()
                }
                val topRatedDeferred = async {
                    api.getMovies(CategoryType.TOP_RATED.value).results?.map {
                        it.toDomain()
                    } ?: emptyList()
                }
                val upcomingDeferred = async {
                    api.getMovies(CategoryType.UPCOMING.value).results?.map {
                        it.toDomain()
                    } ?: emptyList()
                }

                val popularMovies = popularDeferred.await()
                val topRatedMovies = topRatedDeferred.await()
                val upcomingMovies = upcomingDeferred.await()

                listOf(
                    MovieSection(CategoryType.POPULAR, popularMovies),
                    MovieSection(CategoryType.TOP_RATED, topRatedMovies),
                    MovieSection(CategoryType.UPCOMING, upcomingMovies)
                )
            }.toResource()
        }
    }
}