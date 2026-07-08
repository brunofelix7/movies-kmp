package dev.brunofelix.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dev.brunofelix.data.util.NetworkException
import dev.brunofelix.domain.util.Resource
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.error_no_internet
import movies_kmp.shared.generated.resources.error_not_found
import movies_kmp.shared.generated.resources.error_server
import movies_kmp.shared.generated.resources.error_unauthorized
import movies_kmp.shared.generated.resources.error_unknown
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

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

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}