package com.awairhomeassignment.repository

import com.awairhomeassignment.repository.remote.Result
import com.awairhomeassignment.repository.remote.response.EventResponse

interface DataRepository {
    suspend fun networkTest(): Result<EventResponse>
    suspend fun event(nextToken: String?): Result<EventResponse>
}