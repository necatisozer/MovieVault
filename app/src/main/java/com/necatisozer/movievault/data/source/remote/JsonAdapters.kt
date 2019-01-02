package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.remote.tmdb.dto.Movie
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DateAdapter {
    @ToJson
    fun toJson(date: LocalDate) = date.format(DateTimeFormatter.ISO_LOCAL_DATE)

    @FromJson
    fun fromJson(date: String) = DateTimeFormatter.ISO_LOCAL_DATE.parse(date)
}

class StatusAdapter {
    @ToJson
    fun toJson(status: Movie.Status) = status.value

    @FromJson
    fun fromJson(status: String) = Movie.Status.valueOf(status)
}
