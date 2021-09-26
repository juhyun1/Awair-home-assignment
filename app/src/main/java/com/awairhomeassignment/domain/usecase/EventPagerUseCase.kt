package com.awairhomeassignment.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.awairhomeassignment.domain.model.Event
import com.awairhomeassignment.presentation.ui.main.paging.EventDataSource
import com.awairhomeassignment.repository.DataRepository
import com.awairhomeassignment.repository.remote.mapper.EventDtoMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class EventPagerUseCase(
    private val repository: DataRepository,
    private val mapper: EventDtoMapper
) {
    fun execute(scope: CoroutineScope): Flow<PagingData<Event>> {
        return Pager(PagingConfig(pageSize = 5)) {
            EventDataSource(
                repository = repository,
                mapper = mapper
            )
        }.flow.cachedIn(scope)
    }
}