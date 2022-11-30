package ru.surfstudio.i_common.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import ru.surfstudio.i_common.network.retrofit.dto.FilmListResults

interface FilmApi {

    @GET("/3/discover/movie")
    suspend fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): FilmListResults

    @GET("/3/search/movie")
    suspend fun getFilmsBySearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): FilmListResults
}