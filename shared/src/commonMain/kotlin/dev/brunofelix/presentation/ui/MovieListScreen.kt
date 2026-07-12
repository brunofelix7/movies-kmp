package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.brunofelix.domain.model.Movie
import dev.brunofelix.domain.model.MovieSection
import dev.brunofelix.domain.model.enums.CategoryType
import dev.brunofelix.presentation.navigation.MovieDestination
import dev.brunofelix.presentation.state.MovieListState
import dev.brunofelix.presentation.ui.components.ErrorState
import dev.brunofelix.presentation.ui.components.LoadingState
import dev.brunofelix.presentation.ui.components.MovieSectionComponent
import dev.brunofelix.presentation.ui.theme.AppTheme
import dev.brunofelix.presentation.util.UiState
import dev.brunofelix.presentation.util.toReadableMessage
import dev.brunofelix.presentation.viewmodel.MovieListViewModel
import kotlinx.io.IOException
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.populars
import movies_kmp.shared.generated.resources.top_rated
import movies_kmp.shared.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieListRoute(
    navController: NavController,
    viewModel: MovieListViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onCardClick = remember(navController) {
        { id: Int -> navController.navigate(MovieDestination.Detail(id)) }
    }
    val uiState = remember(state) {
        MovieListState(state, onCardClick)
    }
    MovieListScreen(uiState, modifier)
}

@Composable
private fun MovieListScreen(
    uiState: MovieListState,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { paddingValues ->
        val stateModifier = Modifier.fillMaxSize().padding(paddingValues)
        when (val state = uiState.state) {
            is UiState.Loading -> LoadingState(modifier = stateModifier)
            is UiState.Success -> MovieListContent(
                sectionsList = state.data,
                onCardClick = uiState.onCardClick,
                modifier = stateModifier
            )
            is UiState.Error -> ErrorState(
                message = state.exception.toReadableMessage(),
                modifier = stateModifier
            )
        }
    }
}

@Composable
private fun MovieListContent(
    sectionsList: List<MovieSection>,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(sectionsList) { section ->
            val titleRes = when (section.type) {
                CategoryType.POPULAR -> Res.string.populars
                CategoryType.TOP_RATED -> Res.string.top_rated
                CategoryType.UPCOMING -> Res.string.upcoming
            }
            MovieSectionComponent(
                title = stringResource(titleRes),
                movies = section.movies,
                onCardClick = onCardClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    AppTheme {
        MovieListScreen(uiState = MovieListState(
            state = UiState.Loading
        ))
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    AppTheme {
        MovieListScreen(uiState = MovieListState(
            state = UiState.Error(IOException("Error"))
        ))
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessPreview() {
    val movies = listOf(Movie(), Movie(), Movie())
    AppTheme {
        MovieListScreen(uiState = MovieListState(
            state = UiState.Success(listOf(
                MovieSection(
                    type = CategoryType.POPULAR,
                    movies = movies
                ),
                MovieSection(
                    type = CategoryType.TOP_RATED,
                    movies = movies
                ),
                MovieSection(
                    type = CategoryType.UPCOMING,
                    movies = movies
                )
            ))
        ))
    }
}