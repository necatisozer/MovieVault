package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.remote.tmdb.dto.Movie
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DateAdapter {
    @ToJson
    fun toJson(date: LocalDate) = DateTimeFormatter.ISO_LOCAL_DATE.format(date)

    @FromJson
    fun fromJson(date: String) = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
}

class StatusAdapter {
    @ToJson
    fun toJson(status: Movie.Status) = status.value

    @FromJson
    fun fromJson(status: String) = Movie.Status.valueOf(status)
}