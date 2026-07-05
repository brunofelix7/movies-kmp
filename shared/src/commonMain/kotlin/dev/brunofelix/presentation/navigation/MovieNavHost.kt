package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.brunofelix.presentation.ui.MovieListScreen

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MovieDestination.List
    ) {
        composable<MovieDestination.List> {
            MovieListScreen()
        }
        composable<MovieDestination.Detail> {
            // TODO: Create detail screen
        }
    }
}