package com.necatisozer.movievault.data.repository.entity

data class Cast(
    val id: Int,
    val profilePath: String?,
    val name: String,
    val character: String
) : Entity