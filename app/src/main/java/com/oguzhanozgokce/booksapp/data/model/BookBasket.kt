package com.oguzhanozgokce.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_basket_database")
data class BookBasket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "fiyat")
    val fiyat: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "indirim")
    val indirim: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "yayin")
    val yayin: String,

    @ColumnInfo(name = "yazar")
    val yazar: String
)
