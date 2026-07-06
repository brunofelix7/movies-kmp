package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.model.enums.CategoryType
import dev.brunofelix.presentation.mapper.toUiState
import dev.brunofelix.presentation.ui.components.MovieSection
import dev.brunofelix.presentation.ui.resources.AppTheme
import dev.brunofelix.presentation.util.UiState
import dev.brunofelix.presentation.util.toReadableMessage
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.populars
import movies_kmp.shared.generated.resources.top_rated
import movies_kmp.shared.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieListRoute(
    state: UiState<List<MovieSection>> = UiState.Loading,
    onNavigateToDetails: (id: Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Instanciação manual (temporária para testes)
    MovieListScreen(
        uiState = state,
        modifier = modifier,
        onMovieClick = onNavigateToDetails
    )
}

@Composable
fun MovieListScreen(
    uiState: UiState<List<MovieSection>>,
    onMovieClick: (id: Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        when (uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(uiState.data) { section ->
                        val titleRes = when (section.sectionType) {
                            CategoryType.POPULAR -> Res.string.populars
                            CategoryType.TOP_RATED -> Res.string.top_rated
                            CategoryType.UPCOMING -> Res.string.upcoming
                        }
                        MovieSection(
                            title = stringResource(titleRes),
                            movies = section.movies.map { it.toUiState() },
                        )
                    }
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.exception.toReadableMessage()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MovieListScreen(
            uiState = UiState.Loading
        )
    }
}