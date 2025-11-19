package com.genxsol.quiltertest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.genxsol.quiltertest.core.database.entity.BookEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun observeBooks(): Flowable<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<BookEntity>)

    @Query("DELETE FROM books")
    fun clearBooks()

    @Transaction
    fun refresh(books: List<BookEntity>) {
        clearBooks()
        if (books.isNotEmpty()) {
            insertBooks(books)
        }
    }
}

