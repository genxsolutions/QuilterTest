package com.genxsol.quiltertest.core.domain.usecase

import com.genxsol.quiltertest.core.domain.repository.BooksRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RefreshBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    operator fun invoke(): Completable = repository.refresh()
}

