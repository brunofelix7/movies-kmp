package dev.brunofelix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.brunofelix.presentation.ui.MovieListRoute
import dev.brunofelix.presentation.viewmodel.MovieViewModel

fun NavGraphBuilder.movieGraph(
    navController: NavController,
    viewModel: MovieViewModel
) {
    composable<MovieDestination.List> {
        MovieListRoute(navController, viewModel)
    }
    composable<MovieDestination.Detail> {
        // val movieId = it.toRoute<MovieDestination.Details>().id
        // MovieDetailsRoute(id = movieId, onBackClick = onBackClick)
    }
}