package com.example.ballifyandroid.presentation.screens.leagueDetailsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ballifyandroid.R
import com.example.ballifyandroid.presentation.ApiResponse
import com.example.ballifyandroid.presentation.components.FixtureCell
import com.example.ballifyandroid.presentation.components.TeamCell

private const val TAG = "LeagueDetailsScreen"

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueDetailsScreen(
    setTopBar: (@Composable () -> Unit) -> Unit,
    screenDimension: Pair<Float, Float>,
    sportName: String,
    leagueName: String,
    leagueId: Int,
    viewModel: LeagueDetailsViewModel = hiltViewModel()
) {

    val upcomingEventsState by viewModel.upcomingEvents.collectAsState()
    val latestEventsState by viewModel.latestEvents.collectAsState()
    val teamsState by viewModel.teams.collectAsState()


    LaunchedEffect(Unit) {
        setTopBar {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.moss_200),
                ),
                title = {
                    Text(
                        leagueName,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        }
        viewModel.getAllFixtures(leagueId, sportName)
        viewModel.getLeagueTeams(leagueId)
    }


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

            ApiResponse.Loading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {

                CircularProgressIndicator()
            }
            is ApiResponse.Success -> {
                val upcomingEvents = (upcomingEventsState as ApiResponse.Success).data

                if (upcomingEvents.isEmpty()) {
                    Text("No Upcoming Events")
                } else {
                    LazyRow {
                        items(upcomingEvents) { fixture ->
                            FixtureCell(screenDimension, fixture)
                            Spacer(modifier = Modifier.width(10.dp))
                        }
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

            ApiResponse.Loading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {

                CircularProgressIndicator()
            }

            is ApiResponse.Success -> {
                val latestEvents = (latestEventsState as ApiResponse.Success).data
                if (latestEvents.isEmpty()) {
                    Text("No Latest Events")
                } else {
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
        }
        Spacer(modifier = Modifier.height(7.dp))
        Text("Teams", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(7.dp))
        when (teamsState) {
            is ApiResponse.Failure -> {
                Text("No Teams available")
            }

            ApiResponse.Loading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {

                CircularProgressIndicator()
            }
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