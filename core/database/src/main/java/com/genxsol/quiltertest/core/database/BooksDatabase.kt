package com.genxsol.quiltertest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.genxsol.quiltertest.core.database.dao.BookDao
import com.genxsol.quiltertest.core.database.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BookConverters::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

