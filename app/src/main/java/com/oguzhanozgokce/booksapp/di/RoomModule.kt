package com.oguzhanozgokce.booksapp.di

import android.content.Context
import androidx.room.Room
import com.oguzhanozgokce.booksapp.data.room.BookBasketDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): BookBasketDB {
        return Room.databaseBuilder(
            context,
            BookBasketDB::class.java,
            "book_basket_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookBasketDao(
        db: BookBasketDB
    ) = db.bookBasketDaoInstance()
}