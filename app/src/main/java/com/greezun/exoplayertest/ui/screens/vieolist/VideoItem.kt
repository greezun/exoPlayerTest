package com.greezun.exoplayertest.ui.screens.vieolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.greezun.exoplayertest.R
import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.ui.ext.toMinutesAndSeconds


@Composable
fun VideoItem(
    video: VideoEntity,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(video.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.no_image_found),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text("Author: ${video.userName}")
        Text("Size: ${video.width}x${video.height}")
        Text("Duration: ${video.duration.toMinutesAndSeconds()} ")
    }


}

