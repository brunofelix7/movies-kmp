package dev.brunofelix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.brunofelix.presentation.ui.MovieDetailRoute
import dev.brunofelix.presentation.ui.MovieListRoute

fun NavGraphBuilder.movieGraph(
    navController: NavController
) {
    composable<MovieDestination.List> {
        MovieListRoute(
            navController = navController
        )
    }
    composable<MovieDestination.Detail> {
        MovieDetailRoute(
            navController = navController,
            movieId = it.toRoute<MovieDestination.Detail>().id
        )
    }
}