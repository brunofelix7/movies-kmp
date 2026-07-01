package dev.brunofelix.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListDto(
    @SerialName("results")
    val results : List<MovieDto>?
)