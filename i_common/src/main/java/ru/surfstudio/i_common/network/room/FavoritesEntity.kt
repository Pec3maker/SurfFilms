package ru.surfstudio.i_common.network.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("film_id"),
            onDelete = CASCADE
        )
    ]
)
data class FavoritesFilmsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "film_id") val filmId: Int
)