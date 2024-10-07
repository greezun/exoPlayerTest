package com.greezun.exoplayertest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(url: String) {
    val context = LocalContext.current


    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }


    LaunchedEffect(url) {
        val mediaItem = androidx.media3.common.MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
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


@Preview
@Composable
private fun VideoPlayerPrev() {
    VideoPlayer("")
}