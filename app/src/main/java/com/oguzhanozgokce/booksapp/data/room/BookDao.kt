package com.oguzhanozgokce.booksapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {

    // Verilen kitap listesini veritabanına ekler.
    // Eğer bir kitap zaten mevcutsa, onun yerine yenisini koyar (REPLACE stratejisi).
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    // Veritabanında bulunan tüm kitapları döner.
    @Query("SELECT * FROM book_basket_database")
    fun getAllBooks(): LiveData<List<BookEntity>>

    //limit
    @Query("SELECT * FROM book_basket_database LIMIT :limit")
    fun getLimitedBooks(limit: Int): LiveData<List<BookEntity>>

    // Veritabanında favori olarak işaretlenmiş tüm kitapları döner.
    @Query("SELECT * FROM book_basket_database WHERE is_favorite = 1")
    fun getFavoriteBooks(): LiveData<List<BookEntity>>

    // Verilen kitabı veritabanında günceller.
    @Update
    suspend fun updateBook(book: BookEntity)

    // Verilen kitabı veritabanından siler.
    @Delete
    suspend fun deleteBook(book: BookEntity)
}
