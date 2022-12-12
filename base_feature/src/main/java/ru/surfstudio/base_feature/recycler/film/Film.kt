package ru.surfstudio.base_feature.recycler.film

import ru.surfstudio.films_surf.domain.models.Film

/**
 * Данные для рендеринга товара
 *
 * @param film фильм
 * @param isFavourite флаг избранного
 */
data class FilmUi(
    val film: Film,
    val isFavourite: Boolean
)