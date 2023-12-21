package com.remi.player.presentation.musicplaying

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.remi.player.R
import com.remi.player.presentation.utils.component.LoadingDialog
import com.remi.player.presentation.utils.component.SnackBar


@Destination(route = "MusicPlayingScreen")
@Composable
fun MusicPlayingScreen(
    navigator: DestinationsNavigator,
    viewModel: MusicPlayingModel = hiltViewModel(),
) {
    val state = viewModel.state
    LoadingDialog(isShow = state.value.isLoading)
    SnackBar(viewModel = viewModel)
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { navigator.navigateUp() }) {
        Image(
            painter = painterResource(id = R.drawable.music_playing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}