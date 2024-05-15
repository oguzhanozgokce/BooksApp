package com.oguzhanozgokce.booksapp.data.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhanozgokce.booksapp.data.model.BookBasket

@Dao
interface BookBasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookBasket(bookBasket: BookBasket)

    @Query("SELECT * FROM book_basket_database")
    fun getBookBasket(): List<BookBasket>

    @Query("DELETE FROM book_basket_database WHERE id = :id")
    fun deleteBookBasket(id: Int)

}