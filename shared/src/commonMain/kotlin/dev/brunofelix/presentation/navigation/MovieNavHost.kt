package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.brunofelix.presentation.viewmodel.MovieViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    val viewModel = koinViewModel<MovieViewModel>()

    NavHost(
        navController = navController,
        startDestination = MovieDestination.List
    ) {
        movieGraph(
            navController = navController,
            viewModel = viewModel
        )
    }
}