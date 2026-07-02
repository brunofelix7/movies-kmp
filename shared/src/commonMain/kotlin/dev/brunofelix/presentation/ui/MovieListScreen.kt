package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.brunofelix.presentation.state.fakeMovie
import dev.brunofelix.presentation.ui.components.MovieSection
import dev.brunofelix.presentation.ui.resources.AppTheme
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.populars
import movies_kmp.shared.generated.resources.top_rated
import movies_kmp.shared.generated.resources.upcoming
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                MovieSection(
                    title = stringResource(Res.string.populars),
                    movies = List(10) {
                        fakeMovie
                    }
                )
            }
            item {
                MovieSection(
                    title = stringResource(Res.string.upcoming),
                    movies = List(10) {
                        fakeMovie
                    }
                )
            }
            item {
                MovieSection(
                    title = stringResource(Res.string.top_rated),
                    movies = List(10) {
                        fakeMovie
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        MovieListScreen()
    }
}