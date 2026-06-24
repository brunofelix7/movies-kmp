package dev.brunofelix.presentation.navigation

import kotlinx.serialization.Serializable

sealed class MovieRoute {
    @Serializable
    data object ListScreen : MovieRoute()
    
    @Serializable
    data class DetailScreen(val id: Int) : MovieRoute()
}