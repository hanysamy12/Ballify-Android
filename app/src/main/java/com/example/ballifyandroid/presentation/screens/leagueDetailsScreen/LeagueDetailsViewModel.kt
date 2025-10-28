package com.example.ballifyandroid.presentation.screens.leagueDetailsScreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllFixtures(leagueId: Int, sportName : String) {
        val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDate.now()
        val fromDate = currentDate.minusDays(5).format(formater)
        val toDate = currentDate.plusDays(9).format(formater)
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
                .collect { fixtures ->
                    _upcomingEvents.value =
                        ApiResponse.Success(fixtures.filter { it.eventFinalResult?.length == 1 }
                        )
                   _latestEvents.value = ApiResponse.Success(fixtures.filter { it.eventFinalResult?.length != 1 })
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