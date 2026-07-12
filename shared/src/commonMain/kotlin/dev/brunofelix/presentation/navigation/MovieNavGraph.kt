package dev.brunofelix.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.brunofelix.presentation.ui.MovieListRoute
import dev.brunofelix.presentation.util.sharedKoinViewModel
import dev.brunofelix.presentation.viewmodel.MovieListViewModel

fun NavGraphBuilder.movieGraph(
    navController: NavController
) {
    composable<MovieDestination.List> {
        val viewModel = it.sharedKoinViewModel<MovieListViewModel>(navController)

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