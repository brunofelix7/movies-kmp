package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.brunofelix.data.api.KtorClient
import dev.brunofelix.data.api.KtorServiceImpl
import dev.brunofelix.data.repository.MovieRepositoryImpl
import dev.brunofelix.data.source.MovieRemoteDataSourceImpl
import dev.brunofelix.domain.use_case.GetMoviesUseCaseImpl
import dev.brunofelix.presentation.ui.MovieListRoute
import dev.brunofelix.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun NavGraphBuilder.movieGraph(
    onNavigateToDetail: (id: Long) -> Unit,
    onBack: () -> Unit
) {
    composable<MovieDestination.List> {
        val client = KtorClient.client
        val service = KtorServiceImpl(client)
        val dataSource = MovieRemoteDataSourceImpl(service)
        val repository = MovieRepositoryImpl(dataSource, Dispatchers.IO)
        val useCase = GetMoviesUseCaseImpl(repository)
        val viewModel = remember { MovieViewModel(useCase) }
        val state by viewModel.state.collectAsStateWithLifecycle()

        MovieListRoute(state)
    }
    composable<MovieDestination.Detail> {
        // val movieId = it.toRoute<MovieDestination.Details>().id
        // MovieDetailsRoute(id = movieId, onBackClick = onBackClick)
    }
}