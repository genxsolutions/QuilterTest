package com.genxsol.quiltertest.feature.books.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.genxsol.quiltertest.feature.books.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BookCoverImage(
    coverUrl: String?,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(model = coverUrl)
    when (painter.state) {
        is AsyncImagePainter.State.Error, AsyncImagePainter.State.Empty -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.no_cover),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        else -> {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}

