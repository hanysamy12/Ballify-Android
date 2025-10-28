package com.example.ballifyandroid.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ballifyandroid.R
import com.example.ballifyandroid.domain.entity.Fixture

@Composable
fun FixtureCell(screenDimensions: Pair<Float, Float>,fixture: Fixture) {
    val (width, height) = screenDimensions
     val rowHeight = if(height/4 <100) 200f else height/4
    Row(
        modifier = Modifier
            .height(rowHeight.dp)
            .width((width-40).dp)
            .clip(RoundedCornerShape(7.dp))
            .background(colorResource(R.color.light_gray))
    ) {
        Column(
            modifier = Modifier
                .weight(.7f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = fixture.eventHomeTeamLogo,
                contentDescription = fixture.eventHomeTeam,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(10.dp))
            Text(fixture.eventHomeTeam ?:"no available", fontSize = 15.sp, textAlign = TextAlign.Center)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.vs),
                contentDescription = "vs",
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.height(7.dp))
            fixture.eventFinalResult?.let {
                if (it.length > 1) {
                    Text(
                        fixture.eventFinalResult,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.green_500)
                    )
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(fixture.eventDate ?:"not available", fontSize = 15.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(2.dp))
            Text(fixture.eventTime ?:"not available", fontSize = 15.sp, textAlign = TextAlign.Center)

        }
        Column(
            modifier = Modifier
                .weight(.7f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = fixture.eventAwayTeamLogo,
                contentDescription = fixture.eventAwayTeam,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(10.dp))
            Text(fixture.eventAwayTeam ?:"not available", fontSize = 15.sp, textAlign = TextAlign.Center)
        }
    }
}