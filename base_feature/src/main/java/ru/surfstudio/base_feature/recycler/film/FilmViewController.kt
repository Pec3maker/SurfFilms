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
        private val photoIv = binding.posterIv
        private val addToFavoriteIv = binding.cbFavorites
        private val titleTv = binding.titleTv
        private val overviewTv = binding.overviewTv
        private val dateTv = binding.date

        override fun bind(data: FilmUi) {
            val film = data.film
            addToFavoriteIv.isChecked = data.isFavourite
            titleTv.text = film.title
            overviewTv.text = film.overview
            dateTv.text = film.releaseDate ?: ""
            film.posterPath?.let {
                photoIv.load(it)
            }

            itemView.setOnClickListener {
                onFilmClick(film, it)
            }

            addToFavoriteIv.setOnClickListener {
                onFavoriteClick(film)
            }
        }
    }
}
