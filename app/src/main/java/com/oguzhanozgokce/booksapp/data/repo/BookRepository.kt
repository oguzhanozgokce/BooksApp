package com.oguzhanozgokce.booksapp.data.repo

import androidx.lifecycle.LiveData
import com.oguzhanozgokce.booksapp.data.api.BooksServes
import com.oguzhanozgokce.booksapp.data.room.BookEntity
import com.oguzhanozgokce.booksapp.data.room.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookApi: BooksServes,
    private val bookDao: BookDao
) {

    suspend fun fetchBooksFromApi(): Result<List<BookEntity>> {
        return try {
            val response = bookApi.getBooks()
            if (response.isSuccessful) {
                response.body()?.let { bookResponse ->
                    val bookEntities = bookResponse.result.map { result ->
                        BookEntity(
                            fiyat = result.fiyat,
                            image = result.image,
                            indirim = result.indirim,
                            title = result.title,
                            url = result.url,
                            yayin = result.yayın,
                            yazar = result.yazar
                        )
                    }
                    bookDao.insertBooks(bookEntities)
                    Result.success(bookEntities)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception("Failed to fetch books: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getAllBooks(): LiveData<List<BookEntity>> {
        return bookDao.getAllBooks()
    }

    fun getFavoriteBooks(): LiveData<List<BookEntity>> {
        return bookDao.getFavoriteBooks()
    }

    suspend fun updateBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.updateBook(book)
        }
    }

    /**
     * Verilen kitabı veritabanından siler.
     */
    suspend fun deleteBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.deleteBook(book)
        }
    }
}


