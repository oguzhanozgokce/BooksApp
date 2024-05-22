package com.oguzhanozgokce.booksapp.ui.favorite

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
class FavoritesViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _favoriteBooks = MutableLiveData<List<BookEntity>?>()
    val favoriteBooks: MutableLiveData<List<BookEntity>?> get() = _favoriteBooks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchFavoriteBooks()
    }

    // Favori kitapları getiren fonksiyon
    private fun fetchFavoriteBooks() {
        _isLoading.value = true
        viewModelScope.launch {
            val favoriteBooksList = repository.getFavoriteBooks().value // LiveData<List<BookEntity>>'yi alın
            _favoriteBooks.value = favoriteBooksList // LiveData<List<BookEntity>>'yi _favoriteBooks'a ata
            _isLoading.value = false
        }
    }

    // Favori kitaptan çıkaran fonksiyon
    fun removeFavoriteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }
}

