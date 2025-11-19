package com.genxsol.quiltertest.feature.books.viewmodel

import com.genxsol.quiltertest.core.common.schedulers.SchedulerProvider
import com.genxsol.quiltertest.core.domain.usecase.ObserveBooksUseCase
import com.genxsol.quiltertest.core.domain.usecase.RefreshBooksUseCase
import com.genxsol.quiltertest.core.model.Book
import com.genxsol.quiltertest.core.common.resources.StringResources
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BooksViewModelTest {

    @MockK(relaxed = true)
    lateinit var observeBooksUseCase: ObserveBooksUseCase

    @MockK(relaxed = true)
    lateinit var refreshBooksUseCase: RefreshBooksUseCase

    @MockK(relaxed = true)
    lateinit var stringResources: StringResources

    private val schedulerProvider = object : SchedulerProvider {
        override val io = Schedulers.trampoline()
        override val computation = Schedulers.trampoline()
        override val main = Schedulers.trampoline()
    }

    private lateinit var viewModel: BooksViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearMocks(observeBooksUseCase, refreshBooksUseCase, stringResources)
    }

    @Test
    fun `emits books on success`() = runBlocking {
        val fakeBooks = listOf(
            Book(
                key = "k1",
                title = "Test Book",
                subtitle = null,
                authors = listOf("Author"),
                coverId = null,
                firstPublishYear = 2020,
                subjects = emptyList(),
                description = null
            )
        )
        every { observeBooksUseCase.invoke() } returns Observable.just(fakeBooks)
        every { refreshBooksUseCase.invoke() } returns Completable.complete()

        every { stringResources.getString(any()) } returns "Error message"
        
        viewModel = BooksViewModel(
            observeBooksUseCase = observeBooksUseCase,
            refreshBooksUseCase = refreshBooksUseCase,
            schedulerProvider = schedulerProvider,
            stringResources = stringResources
        )

        val state = viewModel.uiState.first { !it.isLoading }
        assertEquals(fakeBooks, state.books)
        assertEquals(null, state.errorMessage)
    }

    @Test
    fun `emits error when refresh fails`() = runBlocking {
        val error = RuntimeException("network")
        every { observeBooksUseCase.invoke() } returns Observable.just(emptyList())
        every { refreshBooksUseCase.invoke() } returns Completable.error(error)

        every { stringResources.getString(any()) } returns "Error message"
        
        viewModel = BooksViewModel(
            observeBooksUseCase = observeBooksUseCase,
            refreshBooksUseCase = refreshBooksUseCase,
            schedulerProvider = schedulerProvider,
            stringResources = stringResources
        )

        val state = viewModel.uiState.first { !it.isLoading }
        assertEquals("network", state.errorMessage)
    }
}

