package com.oguzhanozgokce.booksapp.data.repo

import android.util.Log
import com.bumptech.glide.load.engine.Resource
import com.oguzhanozgokce.booksapp.data.api.BooksServes
import com.oguzhanozgokce.booksapp.data.model.Book
import com.oguzhanozgokce.booksapp.data.model.BookBasket
import com.oguzhanozgokce.booksapp.data.model.Result
import com.oguzhanozgokce.booksapp.data.room.BookBasketDao
import kotlin.math.log
import kotlin.math.truncate

class BookRepository(
    private val bookApi: BooksServes,
    private val bookBasketDao: BookBasketDao
) {
    suspend fun getBooks(): List<Result> {
        return try {
            val book = bookApi.getBooks()
            book.result.let { resultList ->
                val bookBaskets = resultList.map { result ->
                    BookBasket(
                        title = result.title,
                        yazar = result.yazar,
                        yayin = result.yayın,
                        fiyat = result.fiyat,
                        indirim = result.indirim,
                        image = result.image,
                        url = result.url
                    )
                }
                bookBasketDao.addBooks(bookBaskets)
                Log.e("BookRepository", "Received ${resultList.size} books from the API")
                Log.e("BookRepository", "Books successfully saved to Room")
                resultList
            }
        } catch (e: Exception) {
            Log.e("BookRepository", "Error on fetching books from the API", e)
            emptyList()
        }
    }

    fun getBooksFromDatabase() = bookBasketDao.getBookBasket()
}