package com.example.ballifyandroid.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ballifyandroid.R
import com.example.ballifyandroid.domain.entity.Team

@Composable
fun TeamCell(
    team: Team,
    screenDimensions: Pair<Float, Float>
) {
    val (width, height) = screenDimensions
    val rowHeight = if (height / 4 < 100) 200f else height / 4

    Column(
        modifier = Modifier
            .height(rowHeight.dp)
            .width((width - 40).dp)
            .clip(RoundedCornerShape(7.dp))
            .background(colorResource(R.color.light_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = team.teamLogo,
            contentDescription = team.teamName,
            modifier = Modifier
                .size((rowHeight/2).dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            team.teamName ?: "no available",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
