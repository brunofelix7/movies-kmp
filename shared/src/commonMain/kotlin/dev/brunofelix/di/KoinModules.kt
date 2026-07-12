package dev.brunofelix.di

import dev.brunofelix.data.api.KtorClient
import dev.brunofelix.data.api.KtorClientImpl
import dev.brunofelix.data.api.KtorService
import dev.brunofelix.data.api.KtorServiceImpl
import dev.brunofelix.data.repository.MovieRepositoryImpl
import dev.brunofelix.data.source.MovieRemoteDataSource
import dev.brunofelix.data.source.MovieRemoteDataSourceImpl
import dev.brunofelix.domain.repository.MovieRepository
import dev.brunofelix.domain.use_case.GetMoviesUseCase
import dev.brunofelix.domain.use_case.GetMoviesUseCaseImpl
import dev.brunofelix.presentation.viewmodel.MovieListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dispatcherModule = module {
    single { Dispatchers.IO }
}

val networkModule = module {
    single<KtorClient> { KtorClientImpl() }
    single<KtorService> { KtorServiceImpl(get()) }
}

val dataModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factory<GetMoviesUseCase> { GetMoviesUseCaseImpl(get()) }
}

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}