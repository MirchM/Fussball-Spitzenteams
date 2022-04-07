package com.mmisoft.repository

import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.network.model.FootballTeamDtoMapper
import com.mmisoft.network.retrofit.FootballTeamRetrofitService

class FootballTeamRepositoryImplementation(
    private val footballService: FootballTeamRetrofitService,
    private val mapper: FootballTeamDtoMapper
): FootballTeamRepository {

    override suspend fun getList(): List<FootballTeam> {
        return mapper.toDomainList(footballService.getFootballTeams())
    }

    override suspend fun get(id: String): FootballTeam {
        return mapper.mapToDomainModel(footballService.getFootballTeams().firstNotNullOf { team ->
            team.takeIf { team.id == id }
        })
    }

}