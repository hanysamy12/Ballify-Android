package com.example.ballifyandroid.presentation.screens.sportsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ballifyandroid.R
import com.example.ballifyandroid.presentation.navigation.ScreenRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(setToBar: (@Composable () -> Unit) -> Unit,navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {

            LaunchedEffect(Unit) {
                setToBar {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorResource(R.color.moss_200),
                        ),
                        title = {
                            Text(
                                "Sports",
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    )
                }
            }
            Row(modifier = Modifier.weight(1f)) {
                GameCard(
                    Modifier
                        .weight(1f)
                        .padding(5.dp)
                    , sportName = "Football"
                    , sportImage = R.drawable.football
                    , onTapped = {
                        navController.navigate(ScreenRoute.Leagues("Football")
                        )
                    }
                )
                GameCard(
                    Modifier
                        .weight(1f)
                        .padding(5.dp)
                    , sportName = "Basketball"
                    , sportImage = R.drawable.basketball
                    , onTapped = {
                        navController.navigate(ScreenRoute.Leagues("Basketball"))
                    }
                )
            }

            Row(modifier = Modifier.weight(1f)) {
                GameCard(
                    Modifier
                        .weight(1f)
                        .padding(5.dp)
                    , sportName = "Cricket"
                    , sportImage = R.drawable.cricket
                    , onTapped = {
                        navController.navigate(ScreenRoute.Leagues("Cricket"))
                    }
                )
                GameCard(
                    Modifier
                        .weight(1f)
                        .padding(5.dp)
                    , sportName = "Tennis"
                    , sportImage = R.drawable.tennis
                    , onTapped = {
                        navController.navigate(ScreenRoute.Leagues("Tennis"))
                    }
                )
            }
        }
    }
}