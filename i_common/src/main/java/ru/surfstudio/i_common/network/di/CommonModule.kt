package ru.surfstudio.i_common.network.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.surfstudio.android.filmssurf.i_common.BuildConfig
import ru.surfstudio.i_common.network.retrofit.FilmApi
import ru.surfstudio.i_common.network.room.FilmDao
import ru.surfstudio.i_common.network.room.FilmDatabase
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val NETWORK_TIMEOUT = 30L //sec
private const val DATABASE_NAME = "filmsDatabase"

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideFilmApi(retrofit: Retrofit): FilmApi {
        return retrofit.create(FilmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmDatabase(@ApplicationContext context: Context): FilmDatabase {
        return Room.databaseBuilder(context, FilmDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFilmDao(db: FilmDatabase): FilmDao {
        return db.filmDao
    }
}