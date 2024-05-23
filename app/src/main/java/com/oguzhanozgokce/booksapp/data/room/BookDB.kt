package com.oguzhanozgokce.booksapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version =2, exportSchema = false)
abstract class BookDB : RoomDatabase(){

    abstract fun bookBasketDaoInstance(): BookDao

}