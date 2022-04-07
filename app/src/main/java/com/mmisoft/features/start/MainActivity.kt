package com.mmisoft.features.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mmisoft.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Testing The EntityMapper from network and back

        val mapper = FootballTeamNetworkMapper()

        val footballTeam = FootballTeam()

        val networkEntity: FootballTeamNetworkEntity = mapper.mapToEntity(footballTeam)

        val fTeam: FootballTeam = mapper.mapFromEntity(networkEntity)

        Log.println(Log.VERBOSE, "TESTING", fTeam.toString())

         */
    }
}