package com.genxsol.quiltertest.core.domain.usecase

import com.genxsol.quiltertest.core.domain.repository.BooksRepository
import com.genxsol.quiltertest.core.model.Book
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ObserveBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    operator fun invoke(): Observable<List<Book>> = repository.streamBooks()
}

