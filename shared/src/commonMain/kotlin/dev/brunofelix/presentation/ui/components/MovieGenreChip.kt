package dev.brunofelix.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieGenreChip(
    genre: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.error
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 1.dp
                )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MovieGenreChip(
        genre = "Action"
    )
}