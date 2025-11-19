package com.genxsol.quiltertest.core.model

data class Book(
    val key: String,
    val title: String,
    val subtitle: String?,
    val authors: List<String>,
    val coverId: Int?,
    val firstPublishYear: Int?,
    val subjects: List<String>,
    val description: String?
) {
    val coverUrl: String?
        get() = coverId?.let { "https://covers.openlibrary.org/b/id/${it}-L.jpg" }
}

