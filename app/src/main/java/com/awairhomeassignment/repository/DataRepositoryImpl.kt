package com.awairhomeassignment.repository

import com.awairhomeassignment.repository.remote.RemoteRepository
import com.awairhomeassignment.repository.remote.Result
import com.awairhomeassignment.repository.remote.response.EventResponse
import javax.inject.Inject

class DataRepositoryImpl@Inject constructor(
    private val remoteRepository: RemoteRepository
) : DataRepository {
    override suspend fun networkTest(): Result<EventResponse> {
        return remoteRepository.networkTest()
    }

    override suspend fun event(nextToken: String?): Result<EventResponse> {
        return remoteRepository.event(nextToken)
    }
}