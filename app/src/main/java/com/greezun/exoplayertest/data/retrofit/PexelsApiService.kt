package com.greezun.exoplayertest.data.retrofit

import com.greezun.exoplayertest.data.Constants
import com.greezun.exoplayertest.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApiService {
    @Headers("Authorization: ${Constants.API_KEY}")
    @GET("v1/collections/iify4rp")
    suspend fun getCollectionVideos(
        @Query("per_page") perPage: Int = 1,
        @Query("sort") sort: String = "desc"
    ): Response<VideoResponse>
}