package com.oguzhanozgokce.booksapp.data.api

import com.oguzhanozgokce.booksapp.common.Constants.API_KEY
import com.oguzhanozgokce.booksapp.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BooksServes {

    @GET("newBook")
    suspend fun getBooks(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") apiKey: String = "apikey $API_KEY"
        ): Response<Book>

}