package com.remi.player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.DestinationsNavHost
import com.remi.player.presentation.NavGraphs
import com.remi.player.presentation.utils.component.LocalActivity
import com.remi.player.presentation.utils.component.LocalSnackbarHostState
import com.remi.player.ui.theme.RemiPlayerTheme
import com.slaviboy.composeunits.initSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSize()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            CompositionLocalProvider(
                LocalSnackbarHostState provides snackbarHostState,
                LocalActivity provides this@MainActivity,
            ) {
                RemiPlayerTheme {
                    Scaffold(
                        modifier = Modifier.background(Color.Black),
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                        content = {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it),
                                color = Color.White
                            ) {
                                DestinationsNavHost(navGraph = NavGraphs.root)
                            }
                        }
                    )
                }
            }
        }
    }
}
