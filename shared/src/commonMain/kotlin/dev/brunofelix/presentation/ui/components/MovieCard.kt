package dev.brunofelix.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.brunofelix.presentation.state.MovieUiState
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.poster_path
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieCard(
    movie: MovieUiState,
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
            Image(
                painter = painterResource(Res.drawable.poster_path),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}