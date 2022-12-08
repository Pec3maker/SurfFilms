package ru.surfstudio.i_common.network.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.surfstudio.android.filmssurf.i_common.BuildConfig
import ru.surfstudio.films_surf.domain.models.Film
import ru.surfstudio.i_common.network.repository.mapper.filmEntityListMapper
import ru.surfstudio.i_common.network.repository.mapper.filmListMapper
import ru.surfstudio.i_common.network.repository.mapper.filmToFilmEntityListMapper
import ru.surfstudio.i_common.network.retrofit.FilmApi
import ru.surfstudio.i_common.network.room.FilmDao
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FilmsRepository @Inject constructor(
    private val api: FilmApi,
    private val filmDao: FilmDao
) {

    private val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()
    private val scope: CoroutineScope = CoroutineScope(coroutineContext)

    private val _filmListFlow = MutableStateFlow(emptyList<Film>())

    private val _loadedFilms: MutableStateFlow<List<Film>> = MutableStateFlow(emptyList())
    val loadedFilms: StateFlow<List<Film>> = _loadedFilms

    private val _favouritesFilmListFlow = MutableStateFlow(emptyList<Film>())
    val favouritesFilmListFlow: StateFlow<List<Film>> = _favouritesFilmListFlow

    init {
        scope.launch {
            getFilmsDatabase().collect { films ->
                _filmListFlow.emit(films)
            }
        }

        scope.launch {
            getFavouriteFilms().collect { favourites ->
                _favouritesFilmListFlow.emit(
                    favourites
                )
            }
        }
    }

    private fun getFilmsDatabase(): Flow<List<Film>> {
        return filmDao.getFilms().map {
            filmEntityListMapper.map(it)
        }
    }

    private fun getFavouriteFilms(): Flow<List<Film>> {
        return filmDao.getFavouriteFilms().map {
            filmEntityListMapper.map(it)
        }
    }

    suspend fun getFilms(page: Int): List<Film> {
        val films = filmListMapper.map(
            api.getFilms(
                apiKey = BuildConfig.API_KEY,
                page = page,
                language = LANGUAGE
            ).results
        )
        _loadedFilms.value = loadedFilms.value + films
        filmDao.insertFilms(filmToFilmEntityListMapper.map(films))
        return _loadedFilms.value
    }

    suspend fun getFilmsBySearch(
        query: String,
        page: Int
    ): List<Film> {
        val films = filmListMapper.map(
            api.getFilmsBySearch(
                apiKey = BuildConfig.API_KEY,
                query = query,
                page = page,
                language = LANGUAGE
            ).results
        )
        filmDao.insertFilms(filmToFilmEntityListMapper.map(films))
        return films
    }

    suspend fun onFavoriteStatusChange(filmId: Int) {
        if (favouritesFilmListFlow.value.any { it.id == filmId }) {
            filmDao.deleteFavourite(filmId)
        } else {
            filmDao.addFavourite(filmId)
        }
    }

    companion object {
        const val LANGUAGE = "ru"
    }
}