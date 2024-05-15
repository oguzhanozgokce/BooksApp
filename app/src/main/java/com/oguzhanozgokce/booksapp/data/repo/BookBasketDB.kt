package com.oguzhanozgokce.booksapp.data.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanozgokce.booksapp.data.model.BookBasket

@Database(entities = [BookBasket::class], version = 1)
abstract class BookBasketDB : RoomDatabase(){

    abstract fun bookBasketDaoInstance(): BookBasketDao

}