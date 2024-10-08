package com.greezun.exoplayertest.ui.screens.videoplayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.greezun.exoplayertest.data.local.entity.VideoEntity

@Composable
fun VideoPlayerScreen(
    index:Int,
    listVideo: List<VideoEntity>
) {

    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    val mediaItems = listVideo.map {  MediaItem.fromUri(it.link) }

    LaunchedEffect(Unit) {
        player.apply {
            setMediaItems(mediaItems)
            seekToDefaultPosition(index)
            prepare()
            play()
        }
    }

    VideoPlayer(player)
}




