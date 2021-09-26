package com.awairhomeassignment.di

import com.awairhomeassignment.repository.DataRepository
import com.awairhomeassignment.repository.DataRepositoryImpl
import com.awairhomeassignment.repository.remote.InterviewAwairService
import com.awairhomeassignment.repository.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository(
        service: InterviewAwairService
    ): RemoteRepository {
        return RemoteRepository(
            service = service
        )
    }
}