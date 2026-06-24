package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MovieRoute.ListScreen
    ) {
        composable<MovieRoute.ListScreen> {
            // MovieListScreen()
        }
        composable<MovieRoute.DetailScreen> {
            // MovieDetailScreen(it.id)
        }
    }
}