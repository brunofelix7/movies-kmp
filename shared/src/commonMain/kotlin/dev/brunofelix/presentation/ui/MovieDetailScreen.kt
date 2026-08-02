package dev.brunofelix.presentation.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import dev.brunofelix.presentation.state.MovieDetailState
import dev.brunofelix.presentation.ui.components.MovieDetailTopBar
import dev.brunofelix.presentation.ui.theme.AppTheme
import dev.brunofelix.presentation.util.UiState

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
        MovieDetailContent()
    }
}

@Composable
private fun MovieDetailContent(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
private fun SuccessPreview() {
    AppTheme {
        MovieDetailScreen(
            state = MovieDetailState()
        )
    }
}