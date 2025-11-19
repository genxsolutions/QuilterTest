package com.genxsol.quiltertest.feature.books.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.genxsol.quiltertest.core.testing.data.sampleBooks
import com.genxsol.quiltertest.feature.books.ui.state.BooksUiState
import org.junit.Rule
import org.junit.Test

class BooksScreenSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6_PRO
    )

    @Test
    fun defaultState() {
        paparazzi.snapshot {
            BooksScreen(
                state = BooksUiState(
                    isLoading = false,
                    books = sampleBooks
                ),
                onBookSelected = {},
                onDismissDetails = {}
            )
        }
    }
}

