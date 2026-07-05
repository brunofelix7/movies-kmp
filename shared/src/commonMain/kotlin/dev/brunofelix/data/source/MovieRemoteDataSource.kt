package dev.brunofelix.data.source

import dev.brunofelix.data.api.KtorService
import dev.brunofelix.data.api.mapper.toDomain
import dev.brunofelix.domain.model.Movie
import dev.brunofelix.domain.model.enums.CategoryType

interface MovieRemoteDataSource {
    suspend fun getPopular(): Result<List<Movie>>
    suspend fun getTopRated(): Result<List<Movie>>
    suspend fun getUpcoming(): Result<List<Movie>>
}

class MovieRemoteDataSourceImpl(
    private val api: KtorService
) : MovieRemoteDataSource {

    override suspend fun getPopular(): Result<List<Movie>> {
        return runCatching {
            api.getMovies(CategoryType.POPULAR.value).results?.map {
                it.toDomain()
            } ?: emptyList()
        }
    }

    override suspend fun getTopRated(): Result<List<Movie>> {
        return runCatching {
            api.getMovies(CategoryType.TOP_RATED.value).results?.map {
                it.toDomain()
            } ?: emptyList()
        }
    }

    override suspend fun getUpcoming(): Result<List<Movie>> {
        return runCatching {
            api.getMovies(CategoryType.UPCOMING.value).results?.map {
                it.toDomain()
            } ?: emptyList()
        }
    }
}