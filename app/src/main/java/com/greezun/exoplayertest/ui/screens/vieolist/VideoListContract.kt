package com.greezun.exoplayertest.ui.screens.vieolist

import com.greezun.exoplayertest.data.local.entity.VideoEntity
import com.greezun.exoplayertest.ui.base.ViewEffect
import com.greezun.exoplayertest.ui.base.ViewEvent
import com.greezun.exoplayertest.ui.base.ViewState

class VideoListContract {

    data class State(val listVideo: List<VideoEntity>) : ViewState

    data class ShowVideo(val id: Int) : ViewEvent

    sealed interface Effect:ViewEffect{
        data object Load:Effect
        data class RunPlayer(val id: Int) : Effect
        data object ShowErrorToast:Effect
    }

}