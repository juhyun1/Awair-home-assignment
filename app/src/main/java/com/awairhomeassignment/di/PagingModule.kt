package com.awairhomeassignment.di

import com.awairhomeassignment.presentation.ui.eventdetails.OverlapEventAdapter
import com.awairhomeassignment.presentation.ui.main.paging.EventAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PagingModule {

    @Provides
    fun provideEventAdapter(): EventAdapter {
        return EventAdapter()
    }

    @Provides
    fun provideOverlapEventAdapter(): OverlapEventAdapter {
        return OverlapEventAdapter()
    }
}