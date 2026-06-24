package dev.brunofelix.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dev.brunofelix.presentation.navigation.MovieNavHost

@Composable
@Preview(showBackground = true)
fun App() {
    MaterialTheme {
        MovieNavHost(
            navController = rememberNavController()
        )
    }
}