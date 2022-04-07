package com.mmisoft.di

import com.mmisoft.network.model.FootballTeamDtoMapper
import com.mmisoft.network.retrofit.FootballTeamRetrofitService
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
object NetworkModule {

    @Singleton
    @Provides
    fun provideFootballTeamMapper(): FootballTeamDtoMapper{
        return FootballTeamDtoMapper()
    }

    @Singleton
    @Provides
    fun provideFootballTeamService(): FootballTeamRetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://public.allaboutapps.at/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(FootballTeamRetrofitService::class.java)
    }
}