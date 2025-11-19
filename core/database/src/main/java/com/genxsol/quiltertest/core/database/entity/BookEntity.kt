package com.genxsol.quiltertest.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val key: String,
    val title: String,
    val subtitle: String?,
    val authors: List<String>,
    val coverId: Int?,
    val firstPublishYear: Int?,
    val subjects: List<String>,
    val description: String?
)

