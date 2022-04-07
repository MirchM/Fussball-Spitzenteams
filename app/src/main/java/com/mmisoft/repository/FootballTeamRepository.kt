package com.mmisoft.repository

import com.mmisoft.domain.model.FootballTeam

interface FootballTeamRepository {

    suspend fun getList(): List<FootballTeam>

    suspend fun get(id: String): FootballTeam
}