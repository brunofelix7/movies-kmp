package dev.brunofelix.presentation.state

data class MovieUiState(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val posterPath: String = ""
)

// Fake objects
val fakeMovie = MovieUiState(
    id = 1,
    title = "Movie 1",
    overview = "Overview 1",
    posterPath = "https://example.com/poster1.jpg"
)