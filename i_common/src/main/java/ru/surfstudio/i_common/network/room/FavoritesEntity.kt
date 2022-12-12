package ru.surfstudio.i_common.network.room

import androidx.room.*
import androidx.room.ForeignKey.NO_ACTION

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("film_id"),
            onDelete = NO_ACTION
        )
    ]
)
data class FavoritesFilmsEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "film_id") val filmId: Int
)