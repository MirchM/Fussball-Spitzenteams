package com.mmisoft.features.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.presentation.components.FootballTeamList
import com.mmisoft.di.viewmodel.FootballTeamListViewModel
import com.mmisoft.ui.theme.TopAppbarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballTeamListFragment: Fragment(){

    private val viewModel: FootballTeamListViewModel by activityViewModels()

    private val showMenu = mutableStateOf(false)

    private var choice: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val footballTeams = viewModel.footballTeams

                val loading = viewModel.loading.value

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Club Details", color = Color.White )
                            },
                            backgroundColor = TopAppbarColor,
                            actions = {
                                IconButton(onClick = { showMenu.value = !showMenu.value }) {
                                    Icon(
                                        Icons.Filled.FilterList,
                                        contentDescription = "Sort by dropdown icon",
                                        tint = Color.White
                                    )
                                }
                                DropdownMenu(
                                    expanded = showMenu.value,
                                    onDismissRequest = { footballTeams.value = sortTeams(++choice, footballTeams.value) },
                                ){
                                    DropdownMenuItem(onClick = {
                                        footballTeams.value = sortTeams(1, footballTeams.value)
                                    }) {
                                        Text(text = "Namen aufst.")
                                    }
                                    DropdownMenuItem(onClick = {
                                        footballTeams.value = sortTeams(2, footballTeams.value)
                                    }) {
                                        Text(text = "Namen abst.")
                                    }
                                    DropdownMenuItem(onClick = {
                                        footballTeams.value = sortTeams(3, footballTeams.value)
                                    }) {
                                        Text(text = "Wert abst.")
                                    }
                                }
                            }
                        )

                    },
                    content = {
                        FootballTeamList(
                            loading = loading,
                            footballTeams = footballTeams.value,
                            navController = findNavController()
                        )
                    },
                )
            }

        }
    }

    private fun sortTeams(choice: Int, footballTeamsToSort: List<FootballTeam>): List<FootballTeam> {
        if(choice == 4){
            this.choice = 1
        }else{
            this.choice = choice
        }
        showMenu.value = false
        return when(this.choice){
            1 -> footballTeamsToSort.sortedBy { it.name }
            2 -> footballTeamsToSort.sortedByDescending { it.name }
            3 -> footballTeamsToSort.sortedByDescending { it.value }
            else -> {
                footballTeamsToSort
            }
        }
    }
}