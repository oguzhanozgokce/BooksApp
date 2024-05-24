package com.oguzhanozgokce.booksapp.ui.books

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.booksapp.data.room.BookEntity
import com.oguzhanozgokce.booksapp.data.repo.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    val books: LiveData<List<BookEntity>> = repository.getAllBooks()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _limitedBooks = MutableLiveData<List<BookEntity>>()
    val limitedBooks: LiveData<List<BookEntity>> get() = _limitedBooks

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.fetchBooksFromApi()
            result.fold(
                onSuccess = { bookList ->
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    _isLoading.value = false
                }
            )
        }
    }

    fun getLimitedBooks(limit: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getLimitedBooks(limit).observeForever { books ->
                _limitedBooks.value = books
                _isLoading.value = false
                Log.d("BooksViewModel", "Limited books size: ${books.size}")
            }
        }
    }


    fun getAllBooks(): LiveData<List<BookEntity>> {
        return repository.getAllBooks()
    }

    // Veritabanındaki favori kitapları dönen fonksiyon
    fun getFavoriteBooks(): LiveData<List<BookEntity>> {
        return repository.getFavoriteBooks()
    }

    // Kitabı favorilere ekleyen veya favorilerden çıkaran fonksiyon
    fun toggleFavorite(book: BookEntity) {
        viewModelScope.launch {
            val updatedBook = book.copy(isFavorite = !book.isFavorite)
            repository.updateBook(updatedBook)
        }
    }

    // Kitabı veritabanından silen fonksiyon
    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }
}