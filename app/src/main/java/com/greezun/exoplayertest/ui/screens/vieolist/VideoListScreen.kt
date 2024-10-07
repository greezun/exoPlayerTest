package com.greezun.exoplayertest.ui.screens.vieolist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.greezun.exoplayertest.R
import com.greezun.exoplayertest.ui.navigation.NavRoutes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun VideoListScreen(
    navHostController: NavHostController,
    viewModel: VideoListVewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effect.onEach {
            when (it) {
                is VideoListContract.Effect.RunPlayer -> navHostController.navigate(
                    NavRoutes.VideoPlayer(
                        it.id
                    )
                )

                VideoListContract.Effect.ShowErrorToast -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_message), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.collect()
    }

    VideoList(viewModel.state.value) {
        viewModel.setEvent(VideoListContract.ShowVideo(it))
    }

}