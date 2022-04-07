package com.mmisoft.network.retrofit

import com.mmisoft.network.model.FootballTeamDto
import retrofit2.http.GET


interface FootballTeamRetrofitService {

    @GET("/hiring/clubs.json")
    suspend fun getFootballTeams(): List<FootballTeamDto>
}