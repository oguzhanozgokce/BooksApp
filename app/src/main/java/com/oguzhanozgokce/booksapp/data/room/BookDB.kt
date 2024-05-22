package com.oguzhanozgokce.booksapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version =1)
abstract class BookDB : RoomDatabase(){

    abstract fun bookBasketDaoInstance(): BookDao

}