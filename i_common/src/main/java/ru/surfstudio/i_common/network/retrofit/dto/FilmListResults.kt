package ru.surfstudio.i_common.network.retrofit.dto

import com.google.gson.annotations.SerializedName

data class FilmListResults(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<FilmResponse>
)