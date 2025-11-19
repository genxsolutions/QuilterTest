package com.genxsol.quiltertest.feature.books.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import com.genxsol.quiltertest.core.model.Book

@Composable
fun BookList(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookSelected: (Book) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books, key = { it.key }) { book ->
            BookCard(
                book = book,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBookSelected(book) }
            )
        }
    }
}

