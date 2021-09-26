package com.awairhomeassignment.di

import com.awairhomeassignment.repository.remote.InterviewAwairService
import com.awairhomeassignment.repository.remote.mapper.EventDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private const val BASE_URL = "https://mobile-app-interview.awair.is/"

    @Singleton
    @Provides
    fun interviewAwairService(): InterviewAwairService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(InterviewAwairService::class.java)
    }

    @Singleton
    @Provides
    fun provideEventDtoMapper(): EventDtoMapper {
        return EventDtoMapper()
    }
}