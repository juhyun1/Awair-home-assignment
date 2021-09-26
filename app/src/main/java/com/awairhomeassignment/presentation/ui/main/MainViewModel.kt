package com.awairhomeassignment.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.awairhomeassignment.domain.model.Event
import com.awairhomeassignment.domain.usecase.EventPagerUseCase
import com.awairhomeassignment.domain.usecase.NetworkTestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var networkTestUseCase: NetworkTestUseCase

    @Inject
    lateinit var eventPagerUseCase: EventPagerUseCase

    val eventPager: Flow<PagingData<Event>> by lazy {
        eventPagerUseCase.execute(scope = viewModelScope)
    }

    init {
        Timber.d("Start Network Test")
    }

    fun networkTest() {
        viewModelScope.launch {
            networkTestUseCase.execute()

        }
    }
}