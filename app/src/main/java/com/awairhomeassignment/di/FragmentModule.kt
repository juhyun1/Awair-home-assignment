package com.awairhomeassignment.di

import com.awairhomeassignment.presentation.ui.main.MainFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object FragmentModule {
    @Provides
    fun provideTopRatedFragment(): MainFragment {
        return MainFragment()
    }
}