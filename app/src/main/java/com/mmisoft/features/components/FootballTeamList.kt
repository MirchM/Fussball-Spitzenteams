package com.mmisoft.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mmisoft.R
import com.mmisoft.domain.model.FootballTeam

@Composable
fun FootballTeamList(
    loading: Boolean,
    footballTeams: List<FootballTeam>,
    navController: NavController
){
    Log.println(Log.VERBOSE, "Loadingg", loading.toString())
    if(loading){
        ListLoading()
    }else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            footballTeams.map { footballTeam ->
                FootballTeamListItem(
                    footballTeam = footballTeam,
                    onClick = {
                        if(footballTeam.id != null){
                            val bundle = Bundle()
                            bundle.putString("footballTeamId", footballTeam.id)
                            bundle.putParcelable("SelectedTeam", footballTeam)
                            navController.navigate(R.id.navigate_details_screen, bundle)
                        }
                        else{
                            Log.println(Log.VERBOSE, "ERROR-footballTeam: ", "Wrong id")
                        }
                    }
                )
            }
        }
    }
}