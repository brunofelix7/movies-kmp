package dev.brunofelix.presentation.mapper

import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.presentation.state.MovieSectionUiState

fun MovieSection.toUiState(): MovieSectionUiState {
    return MovieSectionUiState(
        sectionType = sectionType,
        movies = movies.map { it.toUiState() }
    )
}