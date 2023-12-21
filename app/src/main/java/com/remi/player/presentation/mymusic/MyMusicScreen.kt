package com.remi.player.presentation.mymusic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.remi.player.R
import com.remi.player.data.local.model.Audio
import com.remi.player.presentation.mymusic.component.TrackItem
import com.remi.player.presentation.utils.component.GradientAngle
import com.remi.player.presentation.utils.component.GradientOffset
import com.remi.player.presentation.utils.component.LoadingDialog
import com.remi.player.presentation.utils.component.LocalAppNavigator
import com.remi.player.presentation.utils.component.SnackBar
import com.remi.player.presentation.utils.component.UiText
import com.remi.player.ui.theme.Dimen
import com.remi.player.ui.theme.FontSize
import com.remi.player.ui.theme.LightGray
import com.remi.player.ui.theme.LightGrayTextHint
import com.remi.player.ui.theme.Ratio

@Destination(start = true)
@Composable
fun MyMusicScreen(
    navigator: DestinationsNavigator,
    viewModel: MyMusicModel = hiltViewModel(),
) {
    val state = viewModel.state
    LoadingDialog(isShow = state.value.isLoading)
    SnackBar(viewModel = viewModel)
    Column(modifier = Modifier.padding(top = Dimen.size020)) {
        TopBar(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(Dimen.size020))
        Category(viewModel = viewModel)
        Spacer(modifier = Modifier.height(Dimen.size020))
        TabScreen()
        CompositionLocalProvider(
            LocalAppNavigator provides navigator
        ) {
            SongList(navigator = navigator, listSong = state.value.listAudio)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(state: State<MyMusicState>, viewModel: MyMusicModel) {
    Column(modifier = Modifier.padding(Dimen.size010)) {
        Row(modifier = Modifier.padding(horizontal = Dimen.size010)) {
            Button(
                onClick = { viewModel.showSnackBar(UiText.DynamicString("23423423")) },
                shape = CircleShape,
                modifier = Modifier
                    .width(Dimen.size080)
                    .aspectRatio(Ratio.ratio1d1)
                    .padding(Dimen.size010)
                    .background(Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_adjustment),
                    contentDescription = "ic_adjustment",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.width(Dimen.size020))
            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = FontSize.size025,
                    textAlign = TextAlign.Center
                ),
                value = state.value.searchText,
                onValueChange = viewModel::setSearchText,
                modifier = Modifier
                    .height(Dimen.size080)
                    .fillMaxWidth()
                    .background(
                        color = LightGray,
                        shape = RoundedCornerShape(20)
                    )
                    .clip(RoundedCornerShape(20)),
                interactionSource = interactionSource,
                enabled = true,
                singleLine = true
            ) {
                TextFieldDefaults.DecorationBox(
                    value = state.value.searchText,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(0.dp),
                    prefix =
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = Dimen.size010)
                                .padding(start = Dimen.size020)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null,
                                tint = LightGrayTextHint
                            )
                        }
                    },
                    suffix = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = Dimen.size010)
                                .padding(end = Dimen.size020)
                        ) {
                            Divider(
                                color = LightGrayTextHint, modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Spacer(modifier = Modifier.width(Dimen.size010))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_microphone),
                                contentDescription = null,
                                tint = LightGrayTextHint
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.my_music_screen_place_holder_search),
                            fontSize = FontSize.size025,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun Category(viewModel: MyMusicModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.size020),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val gradientOffset by remember {
            mutableStateOf(GradientOffset(GradientAngle.CW315))
        }
        CategoryItem(
            viewModel = viewModel,
            gradientOffset = gradientOffset,
            startColor = Color(0xffb091e4),
            endColor = Color(0xff773fd4),
            iconId = R.drawable.ic_star,
            textId = R.string.my_music_screen_favorite_category_label
        )
        CategoryItem(
            viewModel = viewModel,
            gradientOffset = gradientOffset,
            startColor = Color(0xff739e94),
            endColor = Color(0xff548a7c),
            iconId = R.drawable.ic_list,
            textId = R.string.my_music_screen_playlist_category_label
        )
        CategoryItem(
            viewModel = viewModel,
            gradientOffset = gradientOffset,
            startColor = Color(0xffd48743),
            endColor = Color(0xffaf611b),
            iconId = R.drawable.ic_recent,
            textId = R.string.my_music_screen_recent_category_label
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    viewModel: MyMusicModel,
    gradientOffset: GradientOffset,
    startColor: Color,
    endColor: Color,
    @DrawableRes iconId: Int,
    @StringRes textId: Int
) {
    Card(
        modifier = Modifier
            .width(Dimen.size300)
            .aspectRatio(Ratio.ratio5d3),
        onClick = { viewModel.showSnackBar(UiText.StringResource(R.string.loading)) }
    ) {
        val gradientBrush = Brush.linearGradient(
            colors = listOf(startColor, endColor),
            start = gradientOffset.start,
            end = gradientOffset.end,
        )
        Box(
            modifier = Modifier
                .background(brush = gradientBrush)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(top = Dimen.size020, start = Dimen.size020)
            ) {
                Icon(
                    modifier = Modifier
                        .width(Dimen.size080)
                        .aspectRatio(Ratio.ratio1d1),
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    stringResource(id = textId),
                    color = Color.White,
                    fontSize = FontSize.size040,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TabScreen() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(id = R.string.my_music_screen_songs_list_type),
        stringResource(id = R.string.my_music_screen_artist_list_type),
        stringResource(id = R.string.my_music_screen_album_list_type),
        stringResource(id = R.string.my_music_screen_recent_category_label),
    )
    Column {
        TabRow(selectedTabIndex = tabIndex, divider = {}) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title, fontSize = FontSize.size040) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
// 0 -> Text(text = "love a girl")
// 1 -> Text(text = "love a girl2")
// 2 -> Text(text = "love a girl3")
// 3 -> Text(text = "love a girl4")
        }
    }
}

@Composable
fun SongList(navigator: DestinationsNavigator, listSong: List<Audio>) {
    Spacer(modifier = Modifier.height(Dimen.size010))
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        listSong.forEach {
            TrackItem(navigator = navigator, image = it.uri, title = it.title, artist = it.artist)
            Spacer(modifier = Modifier.height(Dimen.size010))
        }
    }
}