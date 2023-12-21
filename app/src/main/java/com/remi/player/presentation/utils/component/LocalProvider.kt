package com.remi.player.presentation.utils.component

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.remi.player.MainActivity

val LocalAppNavigator = compositionLocalOf<DestinationsNavigator> {
    error("No navigator provided")
}

val LocalSnackbarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackbarHostState provided") }

val LocalActivity = staticCompositionLocalOf<MainActivity> {
    error("LocalActivity is not present")
}
