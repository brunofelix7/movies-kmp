package dev.brunofelix.data.repository

import dev.brunofelix.data.source.MovieRemoteDataSource
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.model.enums.CategoryType
import dev.brunofelix.domain.repository.MovieRepository
import dev.brunofelix.domain.util.Resource
import dev.brunofelix.domain.util.toResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getMovies(): Resource<List<MovieSection>> {
        return withContext(dispatcher) {
            runCatching {
                val popularDeferred = async { remoteDataSource.getPopular() }
                val topRatedDeferred = async { remoteDataSource.getTopRated() }
                val upcomingDeferred = async { remoteDataSource.getUpcoming() }

                val popularMovies = popularDeferred.await().getOrThrow()
                val topRatedMovies = topRatedDeferred.await().getOrThrow()
                val upcomingMovies = upcomingDeferred.await().getOrThrow()

                listOf(
                    MovieSection(CategoryType.POPULAR, popularMovies),
                    MovieSection(CategoryType.TOP_RATED, topRatedMovies),
                    MovieSection(CategoryType.UPCOMING, upcomingMovies)
                )
            }.toResource()
        }
    }
}