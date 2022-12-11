package ru.surfstudio.base_feature.recycler.film

import android.view.View
import android.view.ViewGroup
import coil.load
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import ru.surfstudio.android.filmssurf.base_feature.R
import ru.surfstudio.android.filmssurf.base_feature.databinding.FilmItemBinding
import ru.surfstudio.films_surf.domain.models.Film

/**
 *  Контроллер фильма
 */
class FilmViewController(
    private val onFilmClick: (Film, View) -> Unit,
    private val onFavoriteClick: (Film) -> Unit
) : BindableItemController<FilmUi, FilmViewController.Holder>() {

    override fun getItemId(data: FilmUi): Any {
        return data.film.id
    }

    override fun createViewHolder(parent: ViewGroup): Holder {
        return Holder(parent)
    }

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<FilmUi>(parent, R.layout.film_item) {

        private val binding = FilmItemBinding.bind(itemView)

        override fun bind(data: FilmUi) {
            val film = data.film

            itemView.setOnClickListener {
                onFilmClick(film, it)
            }

            with(binding) {
                cbFavorites.isChecked = data.isFavourite
                cbFavorites.setOnClickListener {
                    onFavoriteClick(film)
                }

                titleTv.text = film.title
                overviewTv.text = film.overview
                date.text = film.releaseDate ?: ""
                film.posterPath?.let {
                    posterIv.load(it)
                }
            }
        }
    }
}
