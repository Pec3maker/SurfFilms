package ru.surfstudio.f_films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.surfstudio.base_feature.recycler.film.FilmUi
import ru.surfstudio.base_feature.state.UiError
import ru.surfstudio.base_feature.state.UiState
import ru.surfstudio.films_surf.domain.models.Film
import ru.surfstudio.i_common.network.repository.FilmsRepository
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    init {
        loadFilms(STARTING_PAGE)
        observeFavouritesFilms()
    }

    private fun observeFavouritesFilms() {
        viewModelScope.launch {
            filmsRepository.favouritesFilmListFlow.collectLatest { films ->
                _screenState.emit(
                    _screenState.value.copy(
                        films = matchFavourites(
                            _screenState.value.films.map {
                                it.film
                            }
                        )
                    )
                )
            }
        }
    }

    private var curPage = STARTING_PAGE

    private val _screenState: MutableStateFlow<ScreenState> =
        MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _screenState

    val favouritesFilms = filmsRepository.favouritesFilmListFlow

    fun addToFavorite(film: Film) {
        viewModelScope.launch {
            filmsRepository.onFavoriteStatusChange(film.id)
        }
    }

    fun loadNextPage() {
        curPage++
        loadFilms(curPage)
    }

    private fun loadFilms(page: Int) {
        viewModelScope.launch {
            try {
                _screenState.emit(
                    ScreenState(
                        UiState.Success,
                        matchFavourites(filmsRepository.getFilms(page))
                    )
                )
            } catch (err: Exception) {
                _screenState.emit(
                    ScreenState(
                        UiState.Error(UiError.common(err)),
                        _screenState.value.films
                    )
                )
            }
        }
    }

    private fun matchFavourites(source: List<Film>): List<FilmUi> {
        return source.map { film ->
            FilmUi(film, filmsRepository.favouritesFilmListFlow.value.any { it.id == film.id })
        }
    }

    companion object {
        const val STARTING_PAGE = 1
    }
}