package com.greezun.exoplayertest.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.greezun.exoplayertest.R
import com.greezun.exoplayertest.ui.navigation.NavRoutes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.onEach {
            when (it) {
                SplashScreenContract.Effect.NavigateToList -> navHostController.navigate(NavRoutes.VideoList) {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }

                SplashScreenContract.Effect.ShowErrorToast -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_message), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.collect()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = Color.Blue,
            strokeWidth = 10.dp,
            strokeCap = StrokeCap.Round

        )
    }

}