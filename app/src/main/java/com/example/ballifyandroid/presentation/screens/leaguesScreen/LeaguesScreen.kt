package com.example.ballifyandroid.presentation.screens.leaguesScreen

import android.util.Log
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ballifyandroid.R

private const val TAG = "LeaguesScreen"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaguesScreen (
    league: String,
    setTopBar: (@Composable () -> Unit) -> Unit,
     viewModel: LeaguesViewModel = hiltViewModel()
) {
    val uiState by viewModel.leagues.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSportLeagues(league.lowercase())
    }
    Log.i(TAG, "LeaguesScreen/////: $uiState --")

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
    Text("Leagues For $league")
}