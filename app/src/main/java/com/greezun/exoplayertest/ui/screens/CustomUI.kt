package com.greezun.exoplayertest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


@Composable
fun CustomUI(
    url: String,
    videoVM: VideoVM = hiltViewModel()
) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }


    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }

    LaunchedEffect(url) {
        val mediaItem = androidx.media3.common.MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    // Оновлення стану
    LaunchedEffect(player) {

        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                isPlaying = isPlayingNow
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    duration = player.duration
                }
            }

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                reason: Int
            ) {
                super.onPositionDiscontinuity(oldPosition, newPosition, reason)
                currentPosition = player.currentPosition
            }




        })
    }


    AndroidView(
        factory = {
            PlayerView(context).apply {
                useController = false
                this.player = player
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = {
            it.player = player
        }
    )


    VideoControls(isPlaying, currentPosition, duration, player)

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}

@Composable
fun VideoControls(
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    player: ExoPlayer,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            if (isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Place else Icons.Filled.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play"
            )
        }

        Text(
            text = "${(currentPosition / 1000).toInt() / 60}:${(currentPosition / 1000).toInt() % 60}",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        val effectiveDuration = if (duration > 0) duration.toFloat() else 0f

        Slider(
            value = currentPosition.toFloat(),
            onValueChange = { value ->
                player.seekTo(value.toLong())
            },
            valueRange = 0f..effectiveDuration,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Next"
            )
        }
    }
}
