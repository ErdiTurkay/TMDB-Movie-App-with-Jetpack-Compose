package com.example.challenge4.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteBar(
    favoriteStatus: Boolean,
    color: Color,
    method: () -> Unit
) {
    Icon(
        imageVector = Icons.Default.Favorite,
        tint = color,
        contentDescription = "Favorite Icon",
        modifier = Modifier.clickable {
            method()
        }
    )
}
