package dev.brunofelix.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.brunofelix.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(220.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MovieCard(
        movie = Movie()
    )
}