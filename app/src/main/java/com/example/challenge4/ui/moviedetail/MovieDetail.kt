package com.example.challenge4.ui.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.challenge4.Constant
import com.example.challenge4.R
import com.example.challenge4.ui.SharedViewModel

@Composable
fun MovieDetail(
    sharedViewModel: SharedViewModel
) {
    val painter = rememberImagePainter(
        data = Constant.IMAGE_PATH_PREFIX + sharedViewModel.movie!!.backdropPath,
        builder = {
            placeholder(R.drawable.placeholder_movie)
            error(R.drawable.placeholder_movie)
            crossfade(Constant.CROSSFADE_DURATION)
            /*transformations(
                // BlurTransformation(LocalContext.current),
                // RoundedCornersTransformation(50f)
            )*/
        }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = "Logo Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = sharedViewModel.movie!!.title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(sharedViewModel.movie!!.overview)
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Movie Title",
                fontWeight = FontWeight.Bold
            )
            Text("Lorem ipsum".repeat(20))
        }
    }
}
