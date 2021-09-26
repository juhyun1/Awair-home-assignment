package com.awairhomeassignment.domain.usecase

import com.awairhomeassignment.repository.DataRepository
import com.awairhomeassignment.repository.remote.Result
import timber.log.Timber

class NetworkTestUseCase(
    private val repository: DataRepository
) {
    suspend fun execute() {

        when(val result = repository.networkTest()) {
            is Result.Success -> {

                result.data!!.events.forEach {
                    Timber.d(it.toString())
                }

                Timber.d("Next Token : ${result.data.next_page_token}")
            }
            is Result.Error -> {

                Timber.d("$result")
            }
        }

    }
}