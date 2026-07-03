package dev.brunofelix.data.api.mapper

import dev.brunofelix.BuildKonfig
import dev.brunofelix.data.api.dto.MovieDto
import dev.brunofelix.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id ?: 0,
        title = title ?: "",
        overview = overview ?: "",
        posterPath = "${BuildKonfig.POSTER_BASE_URL}$posterPath"
    )
}