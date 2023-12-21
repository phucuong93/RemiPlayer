package com.remi.player.presentation.mymusic.component

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.remi.player.presentation.destinations.MusicPlayingScreenDestination
import com.remi.player.ui.theme.Dimen
import com.remi.player.ui.theme.FontSize
import com.remi.player.ui.theme.Ratio

@Composable
fun TrackItem(navigator: DestinationsNavigator, image: Uri, title: String, artist: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navigator.navigate(
                MusicPlayingScreenDestination
            )
        }) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(Dimen.size150)
                .aspectRatio(Ratio.ratio1d1)
        )
        Spacer(modifier = Modifier.width(Dimen.size060))
        Column(
            modifier = Modifier.height(Dimen.size150),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = artist,
                fontWeight = FontWeight.Bold,
                fontSize = FontSize.size025
            )
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = FontSize.size040)
        }
    }

}