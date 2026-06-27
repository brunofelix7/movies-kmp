package dev.brunofelix.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.brunofelix.presentation.ui.components.MovieCard

@Preview
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                Column {
                    Text(
                        text = "Populars",
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(5) {
                            MovieCard()
                        }
                    }
                }
            }
        }
    }
}