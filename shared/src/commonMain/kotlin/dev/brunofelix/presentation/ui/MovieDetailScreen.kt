package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import dev.brunofelix.domain.model.Movie
import dev.brunofelix.presentation.state.MovieDetailState
import dev.brunofelix.presentation.ui.components.ErrorState
import dev.brunofelix.presentation.ui.components.LoadingState
import dev.brunofelix.presentation.ui.components.MovieDetailTopBar
import dev.brunofelix.presentation.ui.theme.AppTheme
import dev.brunofelix.presentation.util.UiState
import dev.brunofelix.presentation.util.toReadableMessage

@Composable
fun MovieDetailRoute(
    navController: NavController,
    movieId: Int,
    modifier: Modifier = Modifier
) {
    val state = MovieDetailState(
        uiState = UiState.Loading,
        onBack = { navController.popBackStack() },
        onWatchTrailer = {}
    )
    LaunchedEffect(movieId) {
        Logger.withTag("MovieDetail").i { "movieId: $movieId" }
    }
    MovieDetailScreen(state, modifier)
}

@Composable
private fun MovieDetailScreen(
    state: MovieDetailState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MovieDetailTopBar(state.onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val stateModifier = Modifier.fillMaxSize().padding(paddingValues)
            when (val uiState = state.uiState) {
                is UiState.Loading -> LoadingState(stateModifier)
                is UiState.Success -> {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .weight(1F),
                        shape = MaterialTheme.shapes.large
                    ) {
                        AsyncImage(
                            model = uiState.data.posterPath,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(MaterialTheme.shapes.medium)
                        )
                    }
                }
                is UiState.Error -> {
                    ErrorState(
                        message = uiState.exception.toReadableMessage(),
                        modifier = stateModifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    AppTheme {
        MovieDetailScreen(
            state = MovieDetailState(
                uiState = UiState.Loading
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessPreview() {
    AppTheme {
        MovieDetailScreen(
            state = MovieDetailState(
                uiState = UiState.Success(Movie())
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    AppTheme {
        MovieDetailScreen(
            state = MovieDetailState(
                uiState = UiState.Error(Exception("Error"))
            )
        )
    }
}