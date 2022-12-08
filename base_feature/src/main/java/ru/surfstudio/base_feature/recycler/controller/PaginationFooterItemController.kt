package ru.surfstudio.base_feature.recycler.controller

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.filmssurf.base_feature.R

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

        private val loadingIndicator: ProgressBar =
            itemView.findViewById(R.id.pagination_footer_progress_bar)
        private val showMoreBtn: TextView = itemView.findViewById(R.id.pagination_footer_btn)

        init {
            showMoreBtn.setOnClickListener { listener.onShowMore() }
            loadingIndicator.visibility = View.GONE
            showMoreBtn.visibility = View.GONE
        }

        override fun bind(state: PaginationState) {

            when (state) {
                PaginationState.READY -> {
                    loadingIndicator.visibility = View.VISIBLE
                    showMoreBtn.visibility = View.GONE
                }
                PaginationState.COMPLETE -> {
                    loadingIndicator.visibility = View.GONE
                    showMoreBtn.visibility = View.GONE
                }
                PaginationState.ERROR -> {
                    loadingIndicator.visibility = View.GONE
                    showMoreBtn.visibility = View.VISIBLE
                }
                else -> throw IllegalArgumentException("unsupported state: $state")
            }
        }
    }
}