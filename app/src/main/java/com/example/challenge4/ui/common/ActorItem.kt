package com.example.challenge4.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.challenge4.R
import com.example.challenge4.Constant
import com.example.challenge4.model.Actor
import com.example.challenge4.ui.theme.movieName

@Composable
fun ActorItem(
    actor: Actor,
    method: () -> Unit,
    navController: NavController
) {
    val painter = rememberImagePainter(
        data = actor.profilePath,
        builder = {
            placeholder(R.drawable.placeholder_movie)
            error(R.drawable.placeholder_movie)
            crossfade(Constant.CROSSFADE_DURATION)
            /*transformations(
                //BlurTransformation(LocalContext.current),
                //RoundedCornersTransformation(50f)
            )*/
        }
    )

    var favorite by remember { mutableStateOf(actor.isFavorite) }
    val color = if (favorite) {
        Color.Red
    } else {
        Color.White
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        modifier = Modifier.padding(5.dp)
    ) {
        Column {
            Image(
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .height(3.dp)
                    .fillMaxWidth()
            ) {}
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(5.dp).height(47.dp)
            ) {
                Text(
                    text = actor.originalName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.movieName,
                    maxLines = 2
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.padding(
                end = 5.dp,
                top = 3.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                tint = color,
                contentDescription = "",
                modifier = Modifier.clickable {
                    method()
                    favorite = !favorite
                }
            )
        }
    }
}

@Preview
@Composable
fun ItemActorListPreview() {
    val painter = rememberImagePainter(
        data = "",
        builder = {
            placeholder(R.drawable.placeholder_movie)
            error(R.drawable.placeholder_movie)
            crossfade(500)
            /*transformations(
                //BlurTransformation(LocalContext.current),
                //RoundedCornersTransformation(50f)
            )*/
        }
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        modifier = Modifier.padding(5.dp)
    ) {
        Column {
            Image(
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .height(3.dp)
                    .fillMaxWidth()
            ) {}
            Surface(
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            ) {
                Text(
                    text = "Actor Name",
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}
