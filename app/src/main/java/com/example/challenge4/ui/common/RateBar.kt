package com.example.challenge4.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.challenge4.R
import com.example.challenge4.model.Movie
import com.example.challenge4.ui.theme.rate

@Composable
fun RateBar(
    rateBackground: Color,
    voteAverage: Double
) {
    Surface(
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            Modifier
                .background(rateBackground)
                .padding(3.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_stars_20),
                tint = Color.White,
                contentDescription = "Star Icon"
            )
            Spacer(
                Modifier.width(3.dp)
            )
            Text(
                text = voteAverage.toString(),
                style = MaterialTheme.typography.rate
            )
        }
    }
}