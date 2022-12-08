package ru.surfstudio.i_common.network.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?
)