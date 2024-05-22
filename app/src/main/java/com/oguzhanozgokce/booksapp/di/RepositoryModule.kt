package com.oguzhanozgokce.booksapp.di

import com.oguzhanozgokce.booksapp.data.api.BooksServes
import com.oguzhanozgokce.booksapp.data.repo.BookRepository
import com.oguzhanozgokce.booksapp.data.room.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideBookRepository(
        bookServes: BooksServes,
        booksDao : BookDao, ): BookRepository {
        return BookRepository(bookServes, booksDao)
    }
}