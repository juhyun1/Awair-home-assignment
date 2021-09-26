package com.awairhomeassignment.presentation.ui.main.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.awairhomeassignment.domain.model.Event
import com.awairhomeassignment.repository.DataRepository
import com.awairhomeassignment.repository.remote.Result
import com.awairhomeassignment.repository.remote.mapper.EventDtoMapper
import timber.log.Timber

class EventDataSource(
    private val repository: DataRepository,
    private val mapper: EventDtoMapper
) : PagingSource<Int, Event>() {

    private var nextToken: String? = null
    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        when(val result = repository.event(nextToken)) {
            is Result.Success -> {
                val nextPageNumber = params.key ?: 0
                nextToken = result.data!!.next_page_token

                Timber.d("Next Token : $nextToken")
                // Convert event dtos into domain models.
                val list =  mapper.toDomainList(result.data.events)

                return LoadResult.Page(
                    // Sort by start time.
                    data = list.sortedWith { o1, o2 ->
                        o1.start.compareTo(o2.start)
                    },
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    //If nextToken is null, no more data is requested.
                    nextKey = if (nextToken.isNullOrEmpty()) null else nextPageNumber + 1
                )
            }
            is Result.Error -> {
                Timber.d("$result")
                return LoadResult.Error(result.error)
            }
        }
    }
}
