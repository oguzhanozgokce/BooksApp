package com.oguzhanozgokce.booksapp.data.room

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhanozgokce.booksapp.data.model.BookBasket

@Dao
interface BookBasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBooks(bookBaskets: List<BookBasket>) {
        Log.e("BookBasketDao", "Adding ${bookBaskets.size} books to Room")
        // Add books to Room
    }

    @Query("SELECT * FROM book_basket_database")
    fun getBookBasket(): List<BookBasket>

    @Query("DELETE FROM book_basket_database WHERE id = :id")
    fun deleteBookBasket(id: Int)
}