package com.greezun.exoplayertest.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.greezun.exoplayertest.data.retrofit.PexelsApiService
import com.greezun.exoplayertest.data.tools.NetworkListener
import com.greezun.exoplayertest.domain.VideoRepository
import com.greezun.exoplayertest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val apiService: PexelsApiService,
    private val repository: VideoRepository,
    networkListener: NetworkListener
) : BaseViewModel<SplashScreenContract.Event, SplashScreenContract.State, SplashScreenContract.Effect>() {

    private val netAvailable = networkListener.isAvailable

    private fun updateVideoList() {
        viewModelScope.launch {
            try {
                val response = apiService.getCollectionVideos(10)
                if (response.isSuccessful) {
                    val videos = response.body()
                    videos?.media?.forEach {
                        repository.saveVideo(it)
                    }
                    setEffect { SplashScreenContract.Effect.NavigateToList }
                } else {
                    handleEmptyRepository()
                }

            } catch (e: Exception) {
                handleEmptyRepository()
            }
        }
    }

    private fun handleEmptyRepository() {
        viewModelScope.launch {
            if (repository.isEmpty()) {
                setEffect { SplashScreenContract.Effect.ShowErrorToast }
            } else {
                setEffect { SplashScreenContract.Effect.NavigateToList }
            }
        }
    }

    init {
        if (netAvailable.value) {
            updateVideoList()
        } else {
            handleEmptyRepository()
        }
    }


    override fun initState() = SplashScreenContract.State

    override fun handleEvent(event: SplashScreenContract.Event) {}

}