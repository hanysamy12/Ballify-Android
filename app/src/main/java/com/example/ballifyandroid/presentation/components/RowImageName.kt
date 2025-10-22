package com.example.ballifyandroid.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ballifyandroid.R
import com.example.ballifyandroid.domain.entity.League

private const val TAG = "RowImageName"
@Composable
 fun RowImageName(league: League) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = colorResource(R.color.light_gray))
            .clickable {
                Log.i(TAG, "RowImageName: League Clicked")
            }

    ) {
        Log.i(TAG, "RowImageName: league Logo ${league.leagueLogo}")
        AsyncImage(
            model = league.leagueLogo,
            contentDescription = "leagueName,Image",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .padding(horizontal = 5.dp, vertical = 2.dp),
            fallback = painterResource(R.drawable.personal_logo),
            error = painterResource(R.drawable.picture_loading_failed)

        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterVertically), verticalArrangement = Arrangement.Center) {
            Text(
                text = league.leagueName ?: "No name available",
                fontSize = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }

}