package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Star
import dev.brunofelix.domain.model.Movie
import dev.brunofelix.presentation.state.MovieDetailState
import dev.brunofelix.presentation.ui.components.ErrorState
import dev.brunofelix.presentation.ui.components.LoadingState
import dev.brunofelix.presentation.ui.components.MovieDetailTopBar
import dev.brunofelix.presentation.ui.components.MovieGenreChip
import dev.brunofelix.presentation.ui.components.MovieInfoLabel
import dev.brunofelix.presentation.ui.components.WatchTrailerButton
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = uiState.data.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            MovieInfoLabel(
                                icon = FontAwesomeIcons.Solid.Star,
                                text = "7.5"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            MovieInfoLabel(
                                icon = FontAwesomeIcons.Solid.Clock,
                                text = "2h 36 min"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            MovieInfoLabel(
                                icon = FontAwesomeIcons.Solid.Calendar,
                                text = "2022"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            MovieGenreChip(
                                genre = "Action"
                            )
                        }
                        WatchTrailerButton(
                            onWatchTrailer = state.onWatchTrailer,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
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