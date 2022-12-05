package ru.surfstudio.i_common.network.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM FilmEntity")
    fun getFilms(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Delete
    suspend fun deleteFilms(films: List<FilmEntity>)

    @Query(
        "SELECT * FROM FilmEntity " +
                "JOIN FavoritesFilmsEntity ON FilmEntity.id = FavoritesFilmsEntity.film_id"
    )
    fun getFavouriteFilms(): Flow<List<FilmEntity>>

    @Query("INSERT INTO FavoritesFilmsEntity (film_id) VALUES (:filmId)")
    suspend fun addFavourite(filmId: Int)

    @Query("DELETE FROM FavoritesFilmsEntity WHERE film_id = :filmId")
    suspend fun deleteFavourite(filmId: Int)
}
