package ru.surfstudio.i_common.network.repository.mapper

import ru.surfstudio.films_surf.base.mapper.ListMapperImpl
import ru.surfstudio.films_surf.base.mapper.Mapper
import ru.surfstudio.films_surf.domain.models.Film
import ru.surfstudio.i_common.network.room.FilmEntity

internal object FilmEntityToFilmMapper : Mapper<FilmEntity, Film> {

    override fun map(input: FilmEntity): Film {
        return Film(
            id = input.id,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            title = input.title
        )
    }
}

internal val filmEntityListMapper = ListMapperImpl(FilmEntityToFilmMapper)

internal object FilmToFilmEntityMapper : Mapper<Film, FilmEntity> {

    override fun map(input: Film): FilmEntity {
        return FilmEntity(
            id = input.id,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            title = input.title
        )
    }
}

internal val filmToFilmEntityListMapper = ListMapperImpl(FilmToFilmEntityMapper)