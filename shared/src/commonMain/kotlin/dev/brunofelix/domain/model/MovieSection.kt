package dev.brunofelix.domain.model

import dev.brunofelix.domain.model.enums.CategoryType

data class MovieSection(
    val type: CategoryType = CategoryType.POPULAR,
    val movies: List<Movie> = emptyList()
)