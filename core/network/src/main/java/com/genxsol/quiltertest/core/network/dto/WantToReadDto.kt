package com.genxsol.quiltertest.core.network.dto

import com.squareup.moshi.Json

data class WantToReadResponseDto(
    @Json(name = "reading_log_entries") val entries: List<ReadingLogEntryDto> = emptyList()
)

data class ReadingLogEntryDto(
    @Json(name = "work") val work: WantToReadWorkDto? = null,
    @Json(name = "logged_date") val loggedDate: String? = null
)

data class WantToReadWorkDto(
    @Json(name = "key") val key: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "author_names") val authorNames: List<String>? = null,
    @Json(name = "first_publish_year") val firstPublishYear: Int? = null,
    @Json(name = "cover_id") val coverId: Int? = null
)

