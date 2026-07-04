package dev.brunofelix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.repository.MovieRepository
import dev.brunofelix.presentation.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<MovieSection>>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        getMoviesSection()
    }

    private fun getMoviesSection() {
        viewModelScope.launch {
            try {
                val movies = repository.getMovies()
                _state.update { UiState.Success(movies) }
            } catch (e: Exception) {
                _state.update { UiState.Error(e.message ?: "Unknown error") }
            }
        }
    }
}