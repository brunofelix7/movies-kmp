package dev.brunofelix.presentation.state

data class MovieUiState(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val posterPath: String = ""
)