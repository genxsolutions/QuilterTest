package com.genxsol.quiltertest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.genxsol.quiltertest.feature.books.navigation.BooksScreen
import com.genxsol.quiltertest.feature.books.navigation.booksNavigation

sealed class Screen(val route: String) {
    data object Books : Screen(BooksScreen.Books.route)
}

@Composable
fun QuilterNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Books.route
    ) {
        booksNavigation(navController = navController)
    }
}

