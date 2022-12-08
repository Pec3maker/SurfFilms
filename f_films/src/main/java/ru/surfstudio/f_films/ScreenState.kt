package ru.surfstudio.f_films

import ru.surfstudio.base_feature.recycler.film.FilmUi
import ru.surfstudio.base_feature.state.UiState

data class ScreenState(
    val state: UiState = UiState.Empty,
    val films: List<FilmUi> = emptyList()
)
