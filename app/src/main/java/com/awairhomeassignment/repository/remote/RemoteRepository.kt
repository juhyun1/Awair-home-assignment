package com.awairhomeassignment.repository.remote

import com.awairhomeassignment.repository.remote.response.EventResponse
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val service: InterviewAwairService
) {
    suspend fun networkTest(): Result<EventResponse> {
        return try {
            val response: EventResponse = service.event()
            if (response.events.isNullOrEmpty()) {
                Result.Success(response)
            } else {
                Result.Error(Exception("Server error"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun event(nextToken: String?): Result<EventResponse> {
        return try {
            val response: EventResponse = if (nextToken.isNullOrEmpty()) {
                service.event()
            } else {
                service.event(nextToken)
            }

            if (response.events.isNullOrEmpty()) {
                Result.Error(Exception("Server error"))
            } else {
                Result.Success(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}