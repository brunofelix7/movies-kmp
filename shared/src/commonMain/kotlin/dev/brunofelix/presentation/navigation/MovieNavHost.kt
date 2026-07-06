package dev.brunofelix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.brunofelix.data.api.KtorClient
import dev.brunofelix.data.api.KtorServiceImpl
import dev.brunofelix.data.repository.MovieRepositoryImpl
import dev.brunofelix.data.source.MovieRemoteDataSourceImpl
import dev.brunofelix.domain.use_case.GetMoviesUseCaseImpl
import dev.brunofelix.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    // Instanciação manual temporária encapsulada com remember
    // para não recriar a infraestrutura em recomposições comuns
    val viewModel: MovieViewModel = remember {
        val client = KtorClient.client
        val service = KtorServiceImpl(client)
        val dataSource = MovieRemoteDataSourceImpl(service)
        val repository = MovieRepositoryImpl(dataSource, Dispatchers.IO)
        val useCase = GetMoviesUseCaseImpl(repository)
        MovieViewModel(useCase)
    }

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