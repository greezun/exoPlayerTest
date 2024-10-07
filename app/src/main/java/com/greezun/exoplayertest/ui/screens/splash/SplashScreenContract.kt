package com.greezun.exoplayertest.ui.screens.splash

import com.greezun.exoplayertest.ui.base.ViewEffect
import com.greezun.exoplayertest.ui.base.ViewEvent
import com.greezun.exoplayertest.ui.base.ViewState

class SplashScreenContract {

    object State:ViewState

    object Event:ViewEvent

    sealed interface Effect:ViewEffect{
        data object ShowErrorToast:Effect
        data object NavigateToList:Effect
    }
}