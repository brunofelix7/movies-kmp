package dev.brunofelix.presentation.state

import dev.brunofelix.domain.util.CategoryType

data class MovieSectionUiState(
    val sectionType: CategoryType = CategoryType.POPULAR,
    val movies: List<MovieUiState> = emptyList()
)