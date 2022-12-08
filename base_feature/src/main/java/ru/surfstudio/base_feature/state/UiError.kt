package ru.surfstudio.base_feature.state

import androidx.annotation.StringRes
import ru.surfstudio.android.filmssurf.base_feature.R

sealed class UiError(
    override val message: String? = null,
    @StringRes val messageRes: Int = R.string.unknown_error_message,
) : Throwable(message) {

    object UnknownHostException : UiError(
        messageRes = R.string.unknown_host_error_message
    )

    data class Common(override val message: String? = null) : UiError(
        messageRes = R.string.unknown_error_message
    )

    companion object {
        fun common(exception: Throwable): UiError =
            when (exception) {
                is java.net.UnknownHostException -> UnknownHostException
                else -> Common(exception.message)
            }
    }
}