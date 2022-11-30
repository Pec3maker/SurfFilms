package ru.surfstudio.i_common.network.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FilmEntity::class, FavoritesFilmsEntity::class],
    version = 1
)
abstract class FilmDatabase : RoomDatabase() {

    abstract val filmDao: FilmDao
}