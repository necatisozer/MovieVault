package com.necatisozer.movievault.extension

// hashCode() for Nullable types
fun Any?.hashCode(): Int = this?.hashCode() ?: 0

