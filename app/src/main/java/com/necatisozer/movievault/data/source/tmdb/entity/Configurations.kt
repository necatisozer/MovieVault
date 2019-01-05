package com.necatisozer.movievault.data.source.tmdb.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Configurations(
    val change_keys: List<String>,
    val images: Images
) : TmdbEntity {
    @JsonClass(generateAdapter = true)
    data class Images(
        val backdrop_sizes: List<String>,
        val base_url: String,
        val logo_sizes: List<String>,
        val poster_sizes: List<String>,
        val profile_sizes: List<String>,
        val secure_base_url: String,
        val still_sizes: List<String>
    ) : TmdbEntity
}