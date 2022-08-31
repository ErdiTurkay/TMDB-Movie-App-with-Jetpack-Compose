package com.example.challenge4.ui.common

import androidx.compose.ui.graphics.Color
import com.example.challenge4.Constant
import com.example.challenge4.ui.theme.Bronze
import com.example.challenge4.ui.theme.Gold
import com.example.challenge4.ui.theme.Silver

fun chooseRateColor(voteAverage: Double): Color {
    return if (voteAverage >= Constant.GOLD_RATE) {
        Gold
    } else if (voteAverage >= Constant.SILVER_RATE) {
        Silver
    } else {
        Bronze
    }
}

fun chooseFavoriteColor(isFavorite: Boolean): Color {
    return if (isFavorite) {
        Color.Red
    } else {
        Color.White
    }
}
