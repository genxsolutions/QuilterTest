package com.genxsol.quiltertest.feature.books.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.genxsol.quiltertest.core.designsystem.theme.QuilterTheme
import com.genxsol.quiltertest.core.model.Book
import com.genxsol.quiltertest.feature.books.R
import com.genxsol.quiltertest.feature.books.ui.components.BookDetailsContent
import com.genxsol.quiltertest.feature.books.ui.components.BookList
import com.genxsol.quiltertest.feature.books.ui.components.EmptyState
import com.genxsol.quiltertest.feature.books.ui.components.ErrorState
import com.genxsol.quiltertest.feature.books.ui.components.LoadingState
import com.genxsol.quiltertest.feature.books.ui.state.BooksUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    state: BooksUiState,
    onBookSelected: (Book) -> Unit,
    onDismissDetails: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    QuilterTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_bar_title)) }
                )
            }
        ) { padding ->
            val modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)

            val errorMessage = state.errorMessage
            when {
                state.isLoading -> LoadingState(modifier = modifier)
                errorMessage != null && state.books.isEmpty() -> ErrorState(
                    message = errorMessage,
                    modifier = modifier
                )
                state.showEmptyState -> EmptyState(modifier = modifier)
                else -> BookList(
                    books = state.books,
                    modifier = modifier,
                    onBookSelected = onBookSelected
                )
            }
        }

        state.selectedBook?.let { book ->
            ModalBottomSheet(onDismissRequest = onDismissDetails) {
                BookDetailsContent(
                    book = book,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}

