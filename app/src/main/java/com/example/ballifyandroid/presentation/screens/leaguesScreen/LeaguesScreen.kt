package com.example.ballifyandroid.presentation.screens.leaguesScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ballifyandroid.R
import com.example.ballifyandroid.presentation.ApiResponse
import com.example.ballifyandroid.presentation.components.RowImageName
import com.example.ballifyandroid.presentation.navigation.ScreenRoute

private const val TAG = "LeaguesScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaguesScreen(
    league: String,
    setTopBar: (@Composable () -> Unit) -> Unit,
    viewModel: LeaguesViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.leagues.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.getSportLeagues(league.lowercase())
        setTopBar {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.moss_200),
                ),
                title = {
                    Text(
                        league,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        }
    }

    when (uiState) {
        is ApiResponse.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { CircularProgressIndicator() }
        }

        is ApiResponse.Failure -> {
            val msg = (uiState as ApiResponse.Failure).toString()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text("Error: $msg") }
        }

        is ApiResponse.Success -> {
            val leagues = (uiState as ApiResponse.Success).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
            ) {
                items(leagues) { league ->
                    RowImageName(league, onLeagueClick = { leagueKey ->
                        Log.i(TAG, "LeaguesScreen: $leagueKey")
                        navController.navigate(ScreenRoute.LeagueDetails(leagueKey))

                    }
                    )
                }
            }
        }
    }


}