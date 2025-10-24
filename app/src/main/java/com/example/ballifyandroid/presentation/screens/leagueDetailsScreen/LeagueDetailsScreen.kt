package com.example.ballifyandroid.presentation.screens.leagueDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ballifyandroid.presentation.components.FixtureCell

@Composable
fun LeagueDetailsScreen(screenDimension: Pair<Float, Float>) {

    Column(modifier = Modifier
        .padding(7.dp)
        .verticalScroll(rememberScrollState())) {
        Text("Upcoming Events", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(10) {
                FixtureCell(screenDimension)
                Spacer(modifier = Modifier.width(10.dp))
            }

        }
        Spacer(modifier = Modifier.height(7.dp))
        Text("Latest Events", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(7.dp))


        Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            repeat(10)
            {
                FixtureCell(screenDimension)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        LazyRow {
            items(10) {
                FixtureCell(screenDimension)
                Spacer(modifier = Modifier.width(10.dp))
            }

        }
        Spacer(modifier = Modifier.height(7.dp))
        Text("Teams", fontSize = 22.sp, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(7.dp))



    }
}