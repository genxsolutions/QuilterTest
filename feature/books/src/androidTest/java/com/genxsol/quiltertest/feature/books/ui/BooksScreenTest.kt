package com.genxsol.quiltertest.feature.books.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.genxsol.quiltertest.core.testing.data.sampleBooks
import com.genxsol.quiltertest.feature.books.ui.state.BooksUiState
import org.junit.Rule
import org.junit.Test

class BooksScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun booksScreen_displaysListContent() {
        composeRule.setContent {
            BooksScreen(
                state = BooksUiState(
                    isLoading = false,
                    books = sampleBooks
                ),
                onBookSelected = {},
                onDismissDetails = {}
            )
        }

        composeRule.onNodeWithText("Compose Architectures").assertIsDisplayed()
        composeRule.onNodeWithText("Reactive Kotlin").assertIsDisplayed()
    }
}

