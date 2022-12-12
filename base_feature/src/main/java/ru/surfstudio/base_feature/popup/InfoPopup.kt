package ru.surfstudio.base_feature.popup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import ru.surfstudio.android.filmssurf.base_feature.R
import ru.surfstudio.android.filmssurf.base_feature.databinding.InfoPopupBinding

/**
 * Всплывающее окно подсказки
 */
class InfoPopup(context: Context) {

    private var binding: InfoPopupBinding
    private val popupWindow: PopupWindow
    private val popupTv: TextView

    init {
        val contentView: View = createContentView(context)
        binding = InfoPopupBinding.bind(contentView)
        popupTv = binding.popupTv
        popupWindow = createPopupWindow(contentView)
    }

    /**
     * Показывает всплывающее окно
     *
     * @param popupArgs аргументы для отображения всплывающего окна
     */
    fun show(popupArgs: PopupArgs) {
        popupTv.setText(popupArgs.helperText, TextView.BufferType.SPANNABLE)
        popupWindow.showAsDropDown(popupArgs.anchorView, 0, 0)
    }

    /***
     * Скрывает всплывающее окно
     */
    fun dismiss() {
        popupWindow.dismiss()
    }

    private fun createContentView(context: Context): View {
        return LayoutInflater
            .from(context)
            .inflate(R.layout.info_popup, null, false)
    }

    private fun createPopupWindow(contentView: View): PopupWindow {
        return PopupWindow(
            contentView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            isOutsideTouchable = true
            setTouchInterceptor { _, _ ->
                dismiss()
                true
            }
        }
    }
}