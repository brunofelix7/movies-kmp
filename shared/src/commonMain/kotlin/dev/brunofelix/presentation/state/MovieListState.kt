package dev.brunofelix.presentation.state

import androidx.compose.runtime.Immutable
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.presentation.util.UiState

@Immutable
data class MovieListState(
    val state: UiState<List<MovieSection>> = UiState.Loading,
    val onCardClick: (id: Int) -> Unit = {}
)