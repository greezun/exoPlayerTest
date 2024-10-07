package com.greezun.exoplayertest.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes {

    @Serializable
    data object Splash : NavRoutes

    @Serializable
    data object VideoList : NavRoutes

    @Serializable
    data class VideoPlayer(
        val id: Long
    ) : NavRoutes


}