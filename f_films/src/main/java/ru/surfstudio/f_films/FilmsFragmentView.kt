package ru.surfstudio.f_films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.item.BindableItem
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.filmssurf.f_films.databinding.FragmentFilmsBinding
import ru.surfstudio.base_feature.popup.InfoPopup
import ru.surfstudio.base_feature.popup.PopupArgs
import ru.surfstudio.base_feature.recycler.controller.PaginationFooterItemController
import ru.surfstudio.base_feature.recycler.film.FilmViewController
import ru.surfstudio.base_feature.state.UiState

@AndroidEntryPoint
class FilmsFragmentView : Fragment() {

    private var binding: FragmentFilmsBinding? = null
    private val viewModel: FilmsViewModel by viewModels()
    private val easyAdapter = EasyPaginationAdapter(PaginationFooterItemController()) {
        viewModel.loadNextPage()
    }
    private val filmViewController = FilmViewController(
        onFilmClick = { film, view ->
            showPopup(
                PopupArgs(
                    view,
                    buildSpannedString {
                        append(
                            film.overview
                                .split("\n")
                                .take(STRING_COUNT)
                                .joinToString("\n")
                        )
                    }
                )
            )
        },
        onFavoriteClick = { viewModel.addToFavorite(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeFilms() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect {
                    val itemList = ItemList.create().apply {
                        addAll(
                            it.films.map { film ->
                                BindableItem(
                                    film,
                                    filmViewController
                                )
                            }
                        )
                    }

                    when (it.state) {
                        UiState.Success -> {
                            easyAdapter.setItems(
                                itemList,
                                PaginationState.READY
                            )
                        }
                        is UiState.Error -> {
                            easyAdapter.setItems(
                                itemList,
                                PaginationState.ERROR
                            )
                        }
                    }
                }
            }
        }
    }

    private fun init() {
        binding?.run {
            with(filmsRv) {
                adapter = easyAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun showPopup(popupArgs: PopupArgs) {
        val popup = InfoPopup(requireContext())
        popup.show(popupArgs)
    }

    companion object {
        const val STRING_COUNT = 4
    }
}