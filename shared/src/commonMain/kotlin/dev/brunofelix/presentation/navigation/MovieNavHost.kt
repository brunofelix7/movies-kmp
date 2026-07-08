package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MovieDestination.List
    ) {
        movieGraph(navController = navController)
    }
}