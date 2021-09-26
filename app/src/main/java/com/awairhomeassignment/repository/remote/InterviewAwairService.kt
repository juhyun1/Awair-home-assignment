package com.awairhomeassignment.repository.remote

import com.awairhomeassignment.repository.remote.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface InterviewAwairService {

    @GET("events")
    suspend fun event(): EventResponse

    @GET("events")
    suspend fun event(
        @Query("next_page_token") nextPageToken: String
    ): EventResponse
}