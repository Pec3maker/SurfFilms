package ru.surfstudio.f_films

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.i_common.network.repository.FilmsRepository
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

}