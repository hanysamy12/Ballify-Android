package com.example.ballifyandroid.presentation.screens.leaguesScreen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ballifyandroid.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaguesScreen(league: String,setTopBar : (@Composable () -> Unit)-> Unit) {
    LaunchedEffect(Unit) {
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
    Text("Leagues For $league")
}