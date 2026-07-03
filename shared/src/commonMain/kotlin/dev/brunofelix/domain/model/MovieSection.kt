package dev.brunofelix.domain.model

import dev.brunofelix.domain.util.CategoryType

data class MovieSection(
    val sectionType: CategoryType = CategoryType.POPULAR,
    val movies: List<Movie> = emptyList()
)