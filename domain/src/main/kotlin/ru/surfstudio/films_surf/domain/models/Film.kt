package ru.surfstudio.films_surf.domain.models

data class Film(
    val id: Int,
    val overview: String,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String
)