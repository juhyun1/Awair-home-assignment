package com.awairhomeassignment.di

import com.awairhomeassignment.domain.usecase.EventPagerUseCase
import com.awairhomeassignment.repository.DataRepository
import com.awairhomeassignment.repository.remote.mapper.EventDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideEventPagerUseCase(
        repository: DataRepository,
        mapper: EventDtoMapper
    ): EventPagerUseCase {
        return EventPagerUseCase(repository = repository, mapper = mapper)
    }
}