package com.example.ballifyandroid.presentation.screens.leagueDetailsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ballifyandroid.presentation.ApiResponse
import com.example.ballifyandroid.presentation.components.FixtureCell
import com.example.ballifyandroid.presentation.components.TeamCell

private const val TAG = "LeagueDetailsScreen"

@Composable
fun LeagueDetailsScreen(
    screenDimension: Pair<Float, Float>,
    leagueId: Int,
    viewModel: LeagueDetailsViewModel = hiltViewModel()
) {

    val upcomingEventsState by viewModel.upcomingEvents.collectAsState()
    val latestEventsState by viewModel.latestEvents.collectAsState()
    val teamsState by viewModel.teams.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getAllFixtures(leagueId)
        viewModel.getLeagueTeams(leagueId)
    }

    Log.i(TAG, "LeagueDetailsScreen UPCOMING: $upcomingEventsState")
    Log.i(TAG, "LeagueDetailsScreen LATEST: $latestEventsState")
    Log.i(TAG, "LeagueDetailsScreen TEAMS: $teamsState")



    Column(
        modifier = Modifier
            .padding(7.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Upcoming Events", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(10.dp))
        when (upcomingEventsState) {
            is ApiResponse.Failure -> {
                Text("No Upcoming Events")
            }

            ApiResponse.Loading -> CircularProgressIndicator()
            is ApiResponse.Success -> {
                val upcomingEvents = (upcomingEventsState as ApiResponse.Success).data

                LazyRow {
                    items(upcomingEvents) { fixture ->
                        FixtureCell(screenDimension, fixture)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(7.dp))
        Text("Latest Events", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(7.dp))

        when (latestEventsState) {
            is ApiResponse.Failure -> {
                Text("No Latest Events")
            }

            ApiResponse.Loading -> CircularProgressIndicator()
            is ApiResponse.Success -> {
                val latestEvents = (latestEventsState as ApiResponse.Success).data

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    repeat(latestEvents.size)
                    {
                        FixtureCell(screenDimension, latestEvents[it])
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(7.dp))
        Text ("Teams", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(7.dp))
        when (teamsState) { //////Teams////
            is ApiResponse.Failure -> {
                Text("No Teams available")
            }

            ApiResponse.Loading -> CircularProgressIndicator()
            is ApiResponse.Success -> {
                val teams = (teamsState as ApiResponse.Success).data

                LazyRow {
                    items(teams) {
                        TeamCell(it, screenDimension)
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                }
            }
        }


    }
}