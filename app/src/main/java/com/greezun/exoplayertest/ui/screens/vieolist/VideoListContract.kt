package com.greezun.exoplayertest.ui.screens.vieolist

import com.greezun.exoplayertest.data.model.Video
import com.greezun.exoplayertest.ui.base.ViewEffect
import com.greezun.exoplayertest.ui.base.ViewEvent
import com.greezun.exoplayertest.ui.base.ViewState

class VideoListContract {

    data class State(val listVideo: List<Video>) : ViewState

    data class ShowVideo(val id: Long) : ViewEvent

    sealed interface Effect:ViewEffect{
        data class RunPlayer(val id: Long) : Effect
        data object ShowErrorToast:Effect

    }



}