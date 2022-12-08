package ru.surfstudio.base_feature.popup

import android.text.SpannedString
import android.view.View

/**
 * Данные для отображения всплывающего окна с подсказкой
 *
 * @param anchorView вью, относительно которого надо разместить всплывающее окно
 * @param helperText текст, который надо отобразить в подсказке
 */
data class PopupArgs(
    val anchorView: View,
    val helperText: SpannedString
)
