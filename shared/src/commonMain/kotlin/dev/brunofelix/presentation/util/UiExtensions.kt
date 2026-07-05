package dev.brunofelix.presentation.util

import androidx.compose.runtime.Composable
import dev.brunofelix.data.util.NetworkException
import dev.brunofelix.domain.util.Resource
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.error_no_internet
import movies_kmp.shared.generated.resources.error_not_found
import movies_kmp.shared.generated.resources.error_server
import movies_kmp.shared.generated.resources.error_unauthorized
import movies_kmp.shared.generated.resources.error_unknown
import org.jetbrains.compose.resources.stringResource

fun <T> Resource<T>.toUiState(): UiState<T> {
    return when (this) {
        is Resource.Success -> UiState.Success(this.data)
        is Resource.Error -> UiState.Error(this.exception)
    }
}

@Composable
fun Throwable.toReadableMessage(): String {
    return when (this) {
        is NetworkException.Unauthorized -> stringResource(Res.string.error_unauthorized)
        is NetworkException.NotFound -> stringResource(Res.string.error_not_found)
        is NetworkException.ServerError -> stringResource(Res.string.error_server)
        is NetworkException.NoInternet -> stringResource(Res.string.error_no_internet)
        else -> stringResource(Res.string.error_unknown)
    }
}