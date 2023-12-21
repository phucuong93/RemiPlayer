package com.remi.player.presentation.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.remi.player.presentation.BaseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SnackBar(viewModel: BaseViewModel) {
    val snackBarHostState = LocalSnackbarHostState.current
    val context = LocalContext.current
    LaunchedEffect(snackBarHostState) {
        viewModel.snackBarUIState.collectLatest { uiText ->
            snackBarHostState.showSnackbar(
                uiText.asString(context),
            )
        }
    }
}
