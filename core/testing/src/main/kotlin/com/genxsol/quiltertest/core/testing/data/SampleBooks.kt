package com.genxsol.quiltertest.core.testing.data

import com.genxsol.quiltertest.core.model.Book

val sampleBooks = listOf(
    Book(
        key = "OL123M",
        title = "Compose Architectures",
        subtitle = "Modern patterns for Android",
        authors = listOf("Ada Lovelace"),
        coverId = null,
        firstPublishYear = 2024,
        subjects = listOf("Android", "Architecture"),
        description = "A dummy record used for previews and tests."
    ),
    Book(
        key = "OL456M",
        title = "Reactive Kotlin",
        subtitle = null,
        authors = listOf("Alan Turing"),
        coverId = null,
        firstPublishYear = 2022,
        subjects = listOf("RxJava", "Kotlin"),
        description = "Another fake entry to verify UI layouts."
    )
)

