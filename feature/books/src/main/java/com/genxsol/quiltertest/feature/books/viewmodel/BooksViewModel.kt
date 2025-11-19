package com.genxsol.quiltertest.feature.books.viewmodel

import androidx.lifecycle.ViewModel
import com.genxsol.quiltertest.core.common.schedulers.SchedulerProvider
import com.genxsol.quiltertest.core.domain.usecase.ObserveBooksUseCase
import com.genxsol.quiltertest.core.domain.usecase.RefreshBooksUseCase
import com.genxsol.quiltertest.core.model.Book
import com.genxsol.quiltertest.core.common.resources.StringResources
import com.genxsol.quiltertest.feature.books.R
import com.genxsol.quiltertest.feature.books.ui.state.BooksUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    observeBooksUseCase: ObserveBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val stringResources: StringResources
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    init {
        observeBookStream(observeBooksUseCase)
        loadBooks()
    }

    private fun observeBookStream(observeBooksUseCase: ObserveBooksUseCase) {
        val disposable = observeBooksUseCase()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.main)
            .subscribe(
                { books ->
                    _uiState.value = _uiState.value.copy(
                        books = books,
                        isLoading = false,
                        errorMessage = null
                    )
                },
                { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: stringResources.getString(R.string.error_unable_to_load_books)
                    )
                }
            )
        disposables.add(disposable)
    }

    fun onBookSelected(book: Book) {
        _uiState.value = _uiState.value.copy(selectedBook = book)
    }

    fun onDismissDetails() {
        _uiState.value = _uiState.value.copy(selectedBook = null)
    }

    private fun loadBooks() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
        val disposable = refreshBooksUseCase()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.main)
            .subscribe(
                { _uiState.value = _uiState.value.copy(isLoading = false) },
                { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: stringResources.getString(R.string.error_failed_to_load_books)
                    )
                }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}

