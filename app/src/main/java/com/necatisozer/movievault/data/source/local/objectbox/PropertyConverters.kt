package com.necatisozer.movievault.data.source.local.objectbox

import io.objectbox.converter.PropertyConverter
import org.threeten.bp.LocalDate

class DateConverter : PropertyConverter<LocalDate, Long> {
    override fun convertToDatabaseValue(entityProperty: LocalDate?) =
        entityProperty?.toEpochDay()

    override fun convertToEntityProperty(databaseValue: Long?) =
        databaseValue?.let { LocalDate.ofEpochDay(it) }
}

class StringListConverter : PropertyConverter<List<String>, String> {
    override fun convertToDatabaseValue(entityProperty: List<String>?) =
        entityProperty?.joinToString(",")

    override fun convertToEntityProperty(databaseValue: String?) =
        databaseValue?.split(",")
}

