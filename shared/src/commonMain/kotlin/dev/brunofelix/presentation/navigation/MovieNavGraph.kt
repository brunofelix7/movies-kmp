package dev.brunofelix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.brunofelix.presentation.ui.MovieListRoute
import dev.brunofelix.presentation.util.sharedKoinViewModel
import dev.brunofelix.presentation.viewmodel.MovieViewModel

fun NavGraphBuilder.movieGraph(
    navController: NavController
) {
    composable<MovieDestination.List> {
        val viewModel = it.sharedKoinViewModel<MovieViewModel>(navController)

        MovieListRoute(
            navController = navController,
            viewModel = viewModel
        )
    }
    composable<MovieDestination.Detail> {
        // val movieId = it.toRoute<MovieDestination.Details>().id
        // MovieDetailsRoute(id = movieId, onBackClick = onBackClick)
    }
}