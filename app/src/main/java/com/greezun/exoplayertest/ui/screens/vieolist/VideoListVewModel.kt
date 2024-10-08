package com.greezun.exoplayertest.ui.screens.vieolist

import androidx.lifecycle.viewModelScope
import com.greezun.exoplayertest.data.tools.NetworkListener
import com.greezun.exoplayertest.domain.VideoRepository
import com.greezun.exoplayertest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListVewModel @Inject constructor(
    private val repository: VideoRepository,
    networkListener: NetworkListener
) : BaseViewModel<VideoListContract.ShowVideo, VideoListContract.State, VideoListContract.Effect>() {

    private val netAvailable = networkListener.isAvailable

    override fun initState() = VideoListContract.State(emptyList())

    override fun handleEvent(event: VideoListContract.ShowVideo) {
        if (netAvailable.value) {
            setEffect { VideoListContract.Effect.RunPlayer(event.id) }
        } else{
            setEffect { VideoListContract.Effect.ShowErrorToast }
        }
    }

    init {
        viewModelScope.launch {
            val videoList = repository.fetchVideo().first()
            setState {
                VideoListContract.State(videoList)
            }
        }
    }
}