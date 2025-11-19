package com.genxsol.quiltertest.core.data.di

import android.content.Context
import androidx.room.Room
import com.genxsol.quiltertest.core.common.schedulers.SchedulerProvider
import com.genxsol.quiltertest.core.data.repository.BooksRepositoryImpl
import com.genxsol.quiltertest.core.data.scheduler.DefaultSchedulerProvider
import com.genxsol.quiltertest.core.database.BooksDatabase
import com.genxsol.quiltertest.core.database.dao.BookDao
import com.genxsol.quiltertest.core.domain.repository.BooksRepository
import com.genxsol.quiltertest.core.network.OpenLibraryService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://openlibrary.org/"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = DefaultSchedulerProvider()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideOpenLibraryService(retrofit: Retrofit): OpenLibraryService =
        retrofit.create(OpenLibraryService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BooksDatabase = Room.databaseBuilder(
        context,
        BooksDatabase::class.java,
        "books-db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideBookDao(database: BooksDatabase): BookDao = database.bookDao()

    @Provides
    @Singleton
    fun provideBooksRepository(
        bookDao: BookDao,
        service: OpenLibraryService,
        schedulerProvider: SchedulerProvider
    ): BooksRepository = BooksRepositoryImpl(
        bookDao = bookDao,
        service = service,
        schedulerProvider = schedulerProvider
    )
}

