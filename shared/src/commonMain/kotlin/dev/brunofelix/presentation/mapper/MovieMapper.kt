package dev.brunofelix.presentation.mapper

import dev.brunofelix.domain.model.Movie
import dev.brunofelix.presentation.state.MovieUiState

fun Movie.toUiState(): MovieUiState{
    return MovieUiState(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath
    )
}