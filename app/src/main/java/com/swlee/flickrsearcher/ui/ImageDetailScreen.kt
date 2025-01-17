package com.swlee.flickrsearcher.ui

import android.text.Html
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.swlee.flickrsearcher.model.SearchImageMetadata

@Composable
fun ImageDetailScreen(
    image: SearchImageMetadata,
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Title: ${image.title}", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = "Author: ${image.author}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "Published: ${image.published}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            /*
            The description is or could be in HTML format.
            "description": " <p><a href=\"https:\/\/www.flickr.com\/people\/macspud\/\">Mac Spud<\/a> posted a photo:<\/p> <p><a href=\"https:\/\/www.flickr.com\/photos\/macspud\/54255022494\/\" title=\"IMG_0999\"><img src=\"https:\/\/live.staticflickr.com\/65535\/54255022494_0ba648ff48_m.jpg\" width=\"240\" height=\"180\" alt=\"IMG_0999\" \/><\/a><\/p> <p>Porcupine<\/p> "
            */
            val descriptionText =
                Html.fromHtml(image.description, Html.FROM_HTML_MODE_LEGACY).toString()
            Text(text = "Description: $descriptionText", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Image(
                painter = rememberAsyncImagePainter(image.imageUrl),
                contentDescription = image.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(all = 0.dp)
            )
        }
    }
}
