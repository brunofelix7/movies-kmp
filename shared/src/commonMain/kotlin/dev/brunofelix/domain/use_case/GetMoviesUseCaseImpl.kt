package dev.brunofelix.domain.use_case

import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.repository.MovieRepository
import dev.brunofelix.domain.util.Resource

fun interface GetMoviesUseCase {
    suspend operator fun invoke(): Resource<List<MovieSection>>
}

class GetMoviesUseCaseImpl(
    private val repository: MovieRepository
) : GetMoviesUseCase {

    override suspend operator fun invoke(): Resource<List<MovieSection>> {
        return repository.getMovies()
    }
}