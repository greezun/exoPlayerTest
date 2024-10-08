package com.greezun.exoplayertest.ui.screens.vieolist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.greezun.exoplayertest.R
import com.greezun.exoplayertest.ui.screens.videoplayer.VideoPlayerScreen

@Composable
fun VideoListScreen(
    viewModel: VideoListVewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val effect by viewModel.effect.collectAsState(VideoListContract.Effect.Load)

    var listVisible by remember { mutableStateOf(true) }

    if (listVisible) {
        VideoList(viewModel.state.value) {
            viewModel.setEvent(VideoListContract.ShowVideo(it))
        }
    }

    when (val currentEffect = effect) {
        is VideoListContract.Effect.RunPlayer -> {
            listVisible = false
            VideoPlayerScreen(
                index = currentEffect.id,
                listVideo = viewModel.state.value.listVideo
            )
        }

        VideoListContract.Effect.ShowErrorToast -> {
            Toast.makeText(
                context,
                context.getString(R.string.error_message), Toast.LENGTH_SHORT
            ).show()
        }

        VideoListContract.Effect.Load -> Unit
    }


}