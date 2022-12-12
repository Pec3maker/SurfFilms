package ru.surfstudio.f_films

import ru.surfstudio.base_feature.recycler.film.FilmUi
import ru.surfstudio.base_feature.state.UiState

data class ScreenState(
    val state: UiState = UiState.Success,
    val films: List<FilmUi> = emptyList(),
    val page: Int = INITIAL_PAGE
) {
    companion object {
        const val INITIAL_PAGE = 1
    }
}
