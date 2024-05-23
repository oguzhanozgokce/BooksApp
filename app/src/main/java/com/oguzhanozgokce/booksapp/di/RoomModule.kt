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
            // Eğer sadece `id` sütunu nullable'dan non-nullable yapıldıysa,
            // bu sütunun varsayılan değeri için gerekli SQL'i ekleyin.
            // Bu örnekte `id` sütununun 0 olması gerektiğini varsayıyoruz.

            // DİKKAT: Bu adımı mevcut verileri koruyarak yapmak karmaşık olabilir,
            // çünkü Room migration işlemlerinde mevcut tablolara müdahale etmez.
            // Bunun yerine, geçici bir tablo oluşturabilir ve veri taşınmasını sağlayabilirsiniz.

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
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookBasketDao(
        db: BookDB
    ) = db.bookBasketDaoInstance()
}