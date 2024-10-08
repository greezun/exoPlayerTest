package com.greezun.exoplayertest.ui.screens.videoplayer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(player: ExoPlayer) {

    AndroidView(
        factory = {
            PlayerView(it).apply {
                useController = true
                this.player = player
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = {
            it.player = player
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}

