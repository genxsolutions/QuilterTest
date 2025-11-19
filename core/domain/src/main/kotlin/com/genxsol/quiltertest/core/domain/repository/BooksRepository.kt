package com.genxsol.quiltertest.core.domain.repository

import com.genxsol.quiltertest.core.model.Book
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface BooksRepository {
    fun streamBooks(): Observable<List<Book>>
    fun refresh(): Completable
}

