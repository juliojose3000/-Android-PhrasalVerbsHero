package com.loaizasoftware.phrasalverbshero.di.module

import android.content.Context
import com.loaizasoftware.phrasalverbshero.data.api.ApiClient
import com.loaizasoftware.phrasalverbshero.data.repository.PhrasalVerbRepository
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //A module is a class that provides dependencies to the application
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiClient(@ApplicationContext context: Context): ApiClient {
        return ApiClient().also { it.createRetrofit(context) }
    }

    @Singleton //Ensures that only one instance of VerbRepository is created
    @Provides //Provides an instance of VerbRepository
    fun provideVerbRepository(apiClient: ApiClient): VerbRepository {
        return VerbRepository(apiClient.apiService)
    }

    @Singleton //Ensures that only one instance of VerbRepository is created
    @Provides //Provides an instance of VerbRepository
    fun providePhrasalVerbRepository(apiClient: ApiClient): PhrasalVerbRepository {
        return PhrasalVerbRepository(apiClient.apiService)
    }

}