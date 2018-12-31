package com.necatisozer.movievault.data.source.remote.tmdb.dto

import com.necatisozer.movievault.data.source.remote.Dto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Configurations(
    val change_keys: List<String>,
    val images: Images
) : Dto {
    @JsonClass(generateAdapter = true)
    data class Images(
        val backdrop_sizes: List<String>,
        val base_url: String,
        val logo_sizes: List<String>,
        val poster_sizes: List<String>,
        val profile_sizes: List<String>,
        val secure_base_url: String,
        val still_sizes: List<String>
    ) : Dto
}