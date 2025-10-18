package com.example.ballifyandroid.presentation.screens.sportsScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ballifyandroid.R

private const val TAG = "GameCard"
@Composable
fun GameCard(modifier: Modifier, sportName: String, sportImage: Int,onTapped: () -> Unit) {
    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = colorResource(R.color.green_700),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                Log.i(TAG, "GameCard: $sportName")
                onTapped()
            },
        contentAlignment = Alignment.BottomCenter,

    ) {
        Image(
            painter = painterResource(sportImage),
            contentDescription = "football",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.green_700).copy(alpha = 0.7f),
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(

                text = sportName,
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }

    }

}