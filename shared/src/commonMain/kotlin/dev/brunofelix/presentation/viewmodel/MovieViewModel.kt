package dev.brunofelix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.use_case.GetMoviesUseCase
import dev.brunofelix.domain.util.Resource
import dev.brunofelix.presentation.util.UiEvent
import dev.brunofelix.presentation.util.UiState
import dev.brunofelix.presentation.util.toUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<MovieSection>>>(UiState.Loading)
    val state = _state
        .onStart { getMoviesSection() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun getMoviesSection() {
        viewModelScope.launch {
            _state.update { UiState.Loading }
            val uiState = when (val result = getMoviesUseCase()) {
                is Resource.Success -> result.toUiState()
                is Resource.Error -> {
                    _event.send(UiEvent.ShowErrorMessage(result.exception))
                    result.toUiState()
                }
            }
            _state.update { uiState }
        }
    }
}