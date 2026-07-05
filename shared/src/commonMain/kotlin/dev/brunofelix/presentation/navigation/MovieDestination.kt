package dev.brunofelix.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MovieDestination {
    @Serializable
    data object List : MovieDestination
    
    @Serializable
    data class Detail(val id: Int) : MovieDestination
}