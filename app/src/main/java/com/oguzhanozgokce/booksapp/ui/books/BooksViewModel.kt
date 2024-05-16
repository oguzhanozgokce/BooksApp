package com.oguzhanozgokce.booksapp.ui.books

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.booksapp.data.model.BookBasket
import com.oguzhanozgokce.booksapp.data.model.Result
import com.oguzhanozgokce.booksapp.data.repo.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor
    (private val repository: BookRepository) : ViewModel() {

    private val _books = MutableLiveData<List<Result>>()
    val books: LiveData<List<Result>> get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    //repo -> getBooks
    fun getBooks() {
        _isLoading.value = true
        viewModelScope.launch {
            val books = repository.getBooks() // API'den verileri al, Room'a kaydet ve döndür
            Log.e("BooksViewModel", "Received ${books.size} books from the API")
            if (books.isNotEmpty()) {
                _books.value = books
            }
            _isLoading.value = false
        }
    }
}