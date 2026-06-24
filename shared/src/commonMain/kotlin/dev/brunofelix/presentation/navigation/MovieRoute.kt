package dev.brunofelix.presentation.navigation

sealed class MovieRoute {
    data object ListScreen : MovieRoute()
    data class DetailScreen(val id: Int) : MovieRoute()
}