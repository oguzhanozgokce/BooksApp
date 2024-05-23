package com.oguzhanozgokce.booksapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oguzhanozgokce.booksapp.data.room.BookDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): BookDB {
        return Room.databaseBuilder(
            context,
            BookDB::class.java,
            "book_basket_database.db"
        )
            .addMigrations(MIGRATION_1_2) // Add migrations here
            .build()
    }


    @Provides
    @Singleton
    fun provideBookBasketDao(
        db: BookDB
    ) = db.bookBasketDaoInstance()
}