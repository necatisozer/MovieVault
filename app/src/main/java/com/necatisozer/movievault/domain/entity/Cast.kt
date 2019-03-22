package com.necatisozer.movievault.domain.entity

data class Cast(
    val id: Int,
    val profilePath: String?,
    val name: String,
    val character: String
) : Entity