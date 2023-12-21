package com.remi.player.presentation.utils.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.remi.player.R
import com.remi.player.ui.theme.Dimen
import com.remi.player.utils.TestTags

@Composable
fun LoadingDialog(isShow: Boolean, onDismiss: () -> Unit = {}) {
    if (isShow) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                shape = RoundedCornerShape(20),
                modifier = Modifier.testTag(TestTags.LOADING_DIALOG),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.loading),
                        Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )

                    CircularProgressIndicator(
                        strokeWidth = 4.dp,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(Dimen.size060)
                    )
                }
            }
        }
    }
}
