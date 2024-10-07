package com.greezun.exoplayertest.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greezun.exoplayertest.data.retrofit.PexelsApiService
import com.greezun.exoplayertest.domain.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoVM @Inject constructor(
    apiService: PexelsApiService,
    repository: VideoRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val response = apiService.getCollectionVideos(10)
            val videos = response.body()

            videos?.media?.forEach {
                Log.d("appTAG", "video:  $it")
                repository.saveVideo(it)
            }

            val videosFlow = repository.fetchVideo()
            videosFlow.collectLatest {
                it.forEach { video ->
                    Log.d("appTAG", "videosFlow:  $video")
                }
            }
        }
    }
}