package com.mmisoft.di.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.repository.FootballTeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FootballTeamListViewModel
@Inject constructor(
    private val repository: FootballTeamRepository,
): ViewModel() {

    val footballTeams: MutableState<List<FootballTeam>> = mutableStateOf(listOf())

    val loading = mutableStateOf(true)

    init{
        loadTeams()
    }

    private fun loadTeams(){
        loading.value = true
        viewModelScope.launch {
            val result = repository.getList()
            delay(500)
            footballTeams.value = result
            loading.value = false
        }
    }
}