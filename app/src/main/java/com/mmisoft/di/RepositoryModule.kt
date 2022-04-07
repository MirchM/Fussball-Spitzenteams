package com.mmisoft.di

import com.mmisoft.network.model.FootballTeamDtoMapper
import com.mmisoft.network.retrofit.FootballTeamRetrofitService
import com.mmisoft.repository.FootballTeamRepository
import com.mmisoft.repository.FootballTeamRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFootballTeamRepository(
        footballTeamService: FootballTeamRetrofitService,
        footballTeamMapper: FootballTeamDtoMapper
    ): FootballTeamRepository{
        return FootballTeamRepositoryImplementation(
            footballTeamService, footballTeamMapper
        )
    }
}