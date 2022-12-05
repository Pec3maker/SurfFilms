package ru.surfstudio.i_common.network.repository.mapper

import ru.surfstudio.films_surf.base.mapper.ListMapperImpl
import ru.surfstudio.films_surf.base.mapper.Mapper
import ru.surfstudio.films_surf.domain.models.Film
import ru.surfstudio.i_common.network.retrofit.dto.FilmResponse

internal object FilmResponseMapper : Mapper<FilmResponse, Film> {

    override fun map(input: FilmResponse): Film {
        return Film(
            id = input.id,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            title = input.title
        )
    }
}

internal val filmListMapper = ListMapperImpl(FilmResponseMapper)