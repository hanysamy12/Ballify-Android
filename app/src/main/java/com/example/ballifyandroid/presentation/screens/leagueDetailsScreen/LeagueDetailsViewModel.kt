package com.example.ballifyandroid.presentation.screens.leagueDetailsScreen

import androidx.lifecycle.ViewModel
import com.example.ballifyandroid.domain.entity.Fixture
import com.example.ballifyandroid.domain.entity.Team
import com.example.ballifyandroid.domain.usecase.GetFixturesUseCase
import com.example.ballifyandroid.domain.usecase.GetTeamsUseCase
import com.example.ballifyandroid.presentation.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LeagueDetailsViewModel @Inject constructor(
    private val getFixturesUseCase: GetFixturesUseCase,
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {
    private val _upcomingEvents = MutableStateFlow<ApiResponse<List<Fixture>>>(ApiResponse.Loading)
    val upcomingEvents: MutableStateFlow<ApiResponse<List<Fixture>>> = _upcomingEvents

    private val _latestEvents = MutableStateFlow<ApiResponse<List<Fixture>>>(ApiResponse.Loading)
    val latestEvents: MutableStateFlow<ApiResponse<List<Fixture>>> = _latestEvents

    private val _teams = MutableStateFlow<ApiResponse<List<Team>>>(ApiResponse.Loading)
    val teams: MutableStateFlow<ApiResponse<List<Team>>> = _teams


    suspend fun getAllFixtures(leagueId: Int) {
        val sportName = "football"
        val fromDate = "2025-10-10"
        val toDate = "2025-10-20"
        try {
            getFixturesUseCase.invoke(
                sportName = sportName,
                from = fromDate,
                to = toDate,
                leagueId = leagueId,
            ).map { it.result.orEmpty().filterNotNull() }
                .catch {
                    _upcomingEvents.value = ApiResponse.Failure(it)
                    _latestEvents.value = ApiResponse.Failure(it)
                }
                .collect {
                    _upcomingEvents.value = ApiResponse.Success(it)
                    _latestEvents.value = ApiResponse.Success(it)
                }
        } catch (e: Exception) {
            upcomingEvents.value = ApiResponse.Failure(e)
            latestEvents.value = ApiResponse.Failure(e)
        }

    }

    suspend fun getLeagueTeams(leagueId: Int) {
        val sportName = "football"

        try {
            getTeamsUseCase.invoke(sportName, leagueId)
                .map { it.result.orEmpty().filterNotNull() }
                .catch {
                    _teams.value = ApiResponse.Failure(it)
                }
                .collect {
                    _teams.value = ApiResponse.Success(it)
                }
        } catch (e: Exception) {
            teams.value = ApiResponse.Failure(e)
        }
    }
}