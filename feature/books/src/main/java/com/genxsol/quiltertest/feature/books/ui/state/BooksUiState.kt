package com.genxsol.quiltertest.feature.books.ui.state

import com.genxsol.quiltertest.core.model.Book

data class BooksUiState(
    val isLoading: Boolean = true,
    val books: List<Book> = emptyList(),
    val errorMessage: String? = null,
    val selectedBook: Book? = null
) {
    val showEmptyState: Boolean get() = !isLoading && books.isEmpty() && errorMessage == null
}

