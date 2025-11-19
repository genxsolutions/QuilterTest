package com.genxsol.quiltertest.core.data.repository

import com.genxsol.quiltertest.core.common.schedulers.SchedulerProvider
import com.genxsol.quiltertest.core.database.dao.BookDao
import com.genxsol.quiltertest.core.database.entity.BookEntity
import com.genxsol.quiltertest.core.domain.repository.BooksRepository
import com.genxsol.quiltertest.core.model.Book
import com.genxsol.quiltertest.core.network.OpenLibraryService
import com.genxsol.quiltertest.core.network.dto.ReadingLogEntryDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val service: OpenLibraryService,
    private val schedulerProvider: SchedulerProvider
) : BooksRepository {

    override fun streamBooks(): Observable<List<Book>> {
        return bookDao.observeBooks()
            .toObservable()
            .subscribeOn(schedulerProvider.io)
            .map { entities -> entities.map { it.toDomain() } }
    }

    override fun refresh(): Completable {
        return service.fetchWantToRead()
            .subscribeOn(schedulerProvider.io)
            .map { response -> response.entries.mapNotNull { it.toEntity() } }
            .flatMapCompletable { entities ->
                Completable.fromAction {
                    bookDao.refresh(entities)
                }.subscribeOn(schedulerProvider.io)
            }
            .observeOn(schedulerProvider.main)
    }

    private fun ReadingLogEntryDto.toEntity(): BookEntity? {
        val work = work ?: return null
        val requiredKey = work.key ?: return null
        val requiredTitle = work.title ?: return null
        return BookEntity(
            key = requiredKey,
            title = requiredTitle,
            subtitle = null,
            authors = work.authorNames.orEmpty(),
            coverId = work.coverId,
            firstPublishYear = work.firstPublishYear,
            subjects = emptyList(),
            description = null
        )
    }

    private fun BookEntity.toDomain(): Book {
        return Book(
            key = key,
            title = title,
            subtitle = subtitle,
            authors = authors,
            coverId = coverId,
            firstPublishYear = firstPublishYear,
            subjects = subjects,
            description = description
        )
    }
}

