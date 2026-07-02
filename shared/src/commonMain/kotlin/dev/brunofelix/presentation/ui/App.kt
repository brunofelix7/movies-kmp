package dev.brunofelix.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.rememberNavController
import dev.brunofelix.presentation.navigation.MovieNavHost
import dev.brunofelix.presentation.ui.resources.AppTheme

@Composable
@PreviewLightDark
fun App() {
    AppTheme {
        MovieNavHost(
            navController = rememberNavController()
        )
    }
}