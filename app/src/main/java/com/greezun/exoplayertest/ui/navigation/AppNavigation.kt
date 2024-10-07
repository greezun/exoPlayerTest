package com.greezun.exoplayertest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.greezun.exoplayertest.ui.screens.CustomUI
import com.greezun.exoplayertest.ui.screens.splash.SplashScreen
import com.greezun.exoplayertest.ui.screens.vieolist.VideoListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.Splash
    ) {

        composable<NavRoutes.Splash> {
            SplashScreen(navController)
        }

        composable<NavRoutes.VideoList> {
            VideoListScreen(navController)
        }

        composable<NavRoutes.VideoPlayer> {
            CustomUI("https://videos.pexels.com/video-files/2169879/2169879-sd_960_540_30fps.mp4")
        }

    }

}