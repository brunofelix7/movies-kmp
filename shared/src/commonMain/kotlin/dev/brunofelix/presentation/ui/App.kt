package dev.brunofelix.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.rememberNavController
import dev.brunofelix.di.dataModule
import dev.brunofelix.di.dispatcherModule
import dev.brunofelix.di.networkModule
import dev.brunofelix.di.useCaseModule
import dev.brunofelix.di.viewModelModule
import dev.brunofelix.presentation.navigation.MovieNavHost
import dev.brunofelix.presentation.ui.resources.AppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@PreviewLightDark
fun App() {
    KoinApplication(configuration = koinConfiguration {
        modules(
            dispatcherModule,
            networkModule,
            dataModule,
            useCaseModule,
            viewModelModule
        )
    }) {
        AppTheme {
            MovieNavHost(
                navController = rememberNavController()
            )
        }
    }
}