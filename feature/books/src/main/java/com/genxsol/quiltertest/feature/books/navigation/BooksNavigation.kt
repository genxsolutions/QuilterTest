package com.genxsol.quiltertest.feature.books.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.genxsol.quiltertest.feature.books.ui.BooksScreen
import com.genxsol.quiltertest.feature.books.viewmodel.BooksViewModel

sealed class BooksScreen(val route: String) {
    data object Books : BooksScreen("books")
}

fun NavGraphBuilder.booksNavigation(
    navController: NavController
) {
    composable(route = BooksScreen.Books.route) {
        BooksRoute()
    }
}

@Composable
private fun BooksRoute(
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    
    BooksScreen(
        state = state,
        onBookSelected = viewModel::onBookSelected,
        onDismissDetails = viewModel::onDismissDetails
    )
}

