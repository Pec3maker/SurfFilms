package ru.surfstudio.base_feature.recycler.controller

import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.filmssurf.base_feature.R
import ru.surfstudio.android.filmssurf.base_feature.databinding.LayoutPaginationFooterBinding

class PaginationFooterItemController :
    EasyPaginationAdapter.BasePaginationFooterController<PaginationFooterItemController.Holder>() {

    override fun createViewHolder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener
    ): Holder {
        return Holder(parent, listener)
    }

    inner class Holder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener
    ) : EasyPaginationAdapter.BasePaginationFooterHolder(
        parent,
        R.layout.layout_pagination_footer
    ) {

        private val binding = LayoutPaginationFooterBinding.bind(itemView)

        init {
            with(binding) {
                paginationFooterBtn.setOnClickListener { listener.onShowMore() }
                paginationFooterBtn.visibility = GONE
                paginationFooterProgressBar.visibility = GONE
            }
        }

        override fun bind(state: PaginationState) {

            with(binding) {
                when (state) {
                    PaginationState.READY -> {
                        paginationFooterProgressBar.visibility = View.VISIBLE
                        paginationFooterBtn.visibility = View.GONE
                    }
                    PaginationState.COMPLETE -> {
                        paginationFooterProgressBar.visibility = View.GONE
                        paginationFooterBtn.visibility = View.GONE
                    }
                    PaginationState.ERROR -> {
                        paginationFooterProgressBar.visibility = View.GONE
                        paginationFooterBtn.visibility = View.VISIBLE
                    }
                    else -> throw IllegalArgumentException("unsupported state: $state")
                }
            }
        }
    }
}