package dev.brunofelix.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dev.brunofelix.presentation.navigation.MovieNavHost

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun App() {
    MaterialTheme {
        MovieNavHost(
            navController = rememberNavController()
        )
    }
}