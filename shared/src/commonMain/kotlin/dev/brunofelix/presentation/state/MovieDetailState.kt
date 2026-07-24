package dev.brunofelix.presentation.state

import androidx.compose.runtime.Immutable
import dev.brunofelix.domain.model.Movie
import dev.brunofelix.presentation.util.UiState

@Immutable
data class MovieDetailState(
    val uiState: UiState<Movie> = UiState.Loading,
    val onBack: () -> Unit = {},
    val onWatchTrailer: () -> Unit = {}
)